package com.dailystudy.swinglab.service.framework.auth.filter;

import com.dailystudy.swinglab.service.framework.SwinglabConst;
import com.dailystudy.swinglab.service.framework.http.response.exception.http.SwinglabUnauthorizedException;
import com.dailystudy.swinglab.service.framework.utils.StringUtil;
import com.dailystudy.swinglab.service.framework.utils.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean
{
    @Value("${security.permitAll}")
    private List<String> permitAllUris;

    @Override
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 1. token 필요하지 않은 url 스킵 처리
        if (permitAllUris.contains(httpRequest.getRequestURI()))
        {
            log.info("{} : permit all uri", httpRequest.getRequestURI());
            chain.doFilter(httpRequest, response);
            return;
        }

        // 2. Header 확인
        String header = httpRequest.getHeader(SwinglabConst.AUTHORIZATION_HEADER);
        if (StringUtil.isEmptyString(header))
        {
            log.error("{} : {} header is empty.", httpRequest.getRequestURI(), SwinglabConst.AUTHORIZATION_HEADER);
            throw new SwinglabUnauthorizedException("Token 정보가 없습니다.");
        }

        // 3. Token 확인
        String token = TokenUtils.getTokenFromHeader(header);
        if (!TokenUtils.isValidToken(token))
        {
            log.error("{} : invalided token", httpRequest.getRequestURI());
            throw new SwinglabUnauthorizedException("유효하지 않은 Token입니다.");
        }

        // 4. 로그인아이디 확인
        String loginId = TokenUtils.getLoginIdFromToken(token);
        if (StringUtil.isEmptyString(loginId))
        {
            log.error("{} : loginId in token is empty", httpRequest.getRequestURI());
            throw new SwinglabUnauthorizedException("유효하지 않은 Token입니다.");
        }

        // TODO TokenUtil -> JwtTokenProvider로 변경 하고 -> 여기서  SecurityContextHolder에 인증객체 세팅하기.
//        SecurityContextHolder.getContext().setAuthentication();

        chain.doFilter(httpRequest, response);
    }
}
