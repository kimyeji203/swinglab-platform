package com.dailystudy.swinglab.service.framework.http.request.filter;

import com.dailystudy.swinglab.service.business.common.domain.entity.common.ApiLog;
import com.dailystudy.swinglab.service.business.common.repository.common.ApiLogRepository;
import com.dailystudy.swinglab.service.framework.http.request.HttpRequestThreadLocal;
import com.dailystudy.swinglab.service.framework.http.request.domain.ApiRequestCache;
import com.dailystudy.swinglab.service.framework.http.response.PlatformHttpStatus;
import com.dailystudy.swinglab.service.framework.http.response.domain.ErrorResponse;
import com.dailystudy.swinglab.service.framework.utils.TxidUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * api 이력 filter
 *
 * @author yjkim
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class ApiLoggingFilter extends GenericFilterBean
{
    @Value("${server.servlet.context-path}")
    private String contextPath;

    private final ApiLogRepository apiLogRepository;

    private static final List<String> SKIP_API = Arrays.asList();

    private boolean isSkip (String uri)
    {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String pattern : SKIP_API)
        {
            if (pathMatcher.match(contextPath + pattern, uri))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestUri = httpServletRequest.getRequestURI(); // URI
        if (apiLogRepository == null || isSkip(requestUri))
        {
            chain.doFilter(request, response);
        } else
        {
            logApi(request, response, chain);
        }
    }

    /**
     * filter chain 실행시 api 호출 이력을 Logging 하여 준다.
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    private void logApi (ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {

        log.debug("ApiLoggingFilter doFilter executed.");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestUri = httpServletRequest.getRequestURI(); // URI

        String transactionId = TxidUtils.initializeTxidIfNotAbsent(httpServletRequest);

        Date start = new Date(); // 수신시간

        chain.doFilter(request, response);

        if (isSkip(requestUri))
        {
            return;
        }

        Long elapsedTimeMillis = System.currentTimeMillis() - start.getTime();

        ApiRequestCache restApiResponse = HttpRequestThreadLocal.getRestApiResponse();

        PlatformHttpStatus httpSttus = restApiResponse.getHttpSttus();
        ErrorResponse errorResponse = restApiResponse.getErrorResponse();
        HttpRequestThreadLocal.remove();

        try
        {
            HttpServletRequest servletRequest = ((HttpServletRequest) request);
            String method = servletRequest.getMethod(); // 요청 메소드
            String reqBody = restApiResponse.getRequestBody(); // 요청 바디
            String resBody = restApiResponse.getResponseBody();
            String clientIp = servletRequest.getHeader("x-client-ip"); // 요청한 서버의 ip
            if (StringUtils.isEmpty(clientIp))
            {
                clientIp = request.getRemoteAddr();
            }

            long contentLength = 0;
            if (response instanceof ContentCachingResponseWrapper)
            {
                contentLength = ((ContentCachingResponseWrapper) response).getContentSize();
            } else
            {
                String cl = ((HttpServletResponse) response).getHeader("Content-Length");
                contentLength = NumberUtils.toLong(cl, 0);
            }

            ApiLog apiLog = new ApiLog();
            apiLog.setReqDt(start);
            apiLog.setResDt(new Date());
            apiLog.setTxId(transactionId);
            apiLog.setClintIp(clientIp);
            apiLog.setApi(StringUtils.join("[", method, "] ", requestUri));
            apiLog.setReqBody(reqBody);
            apiLog.setResBodyLen(contentLength);

            if (ObjectUtils.isEmpty(httpSttus) || httpSttus == PlatformHttpStatus.OK)
            {
                apiLog.setHttpSttusCd(HttpStatus.OK.value());
                apiLog.setResCd(String.valueOf(HttpStatus.OK.value()));
                apiLog.setResYn(true);
                apiLog.setResBody(resBody);
            } else
            {
                apiLog.setHttpSttusCd(httpSttus.value());
                apiLog.setResYn(false);
                apiLog.setResCd(errorResponse.getErrorCode());
                if (errorResponse != null)
                {
                    if (errorResponse.getErrorMessage() != null && errorResponse.getErrorMessage().length() > 500)
                    {
                        apiLog.setResBody(errorResponse.getErrorMessage().substring(0, 500));
                    } else
                    {
                        apiLog.setResBody(errorResponse.getErrorMessage());
                    }
                }
            }

            // API 이력 DB 저장
            apiLogRepository.save(apiLog);
        } catch (Exception ex)
        {
            log.warn(ex.getMessage(), ex);
        }
    }
}
