package com.dailystudy.swinglab.service.framework.auth.filter;

import com.dailystudy.swinglab.service.framework.SwinglabConst;
import com.dailystudy.swinglab.service.framework.auth.JwtTokenProvider;
import com.dailystudy.swinglab.service.framework.http.response.exception.http.SwinglabUnauthorizedException;
import com.dailystudy.swinglab.service.framework.utils.SecurityUtil;
import com.dailystudy.swinglab.service.framework.utils.StringUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter
{
    @Value("${security.permitAll}")
    private List<String> permitAllUris;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        // 1. token 필요하지 않은 url 스킵 처리
        if (permitAllUris.contains(request.getRequestURI()))
        {
            log.info("{} : permit all uri", request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Header 확인
        String header = request.getHeader(SwinglabConst.AUTHORIZATION_HEADER);
        if (StringUtil.isEmptyString(header))
        {
            log.error("{} : {} header is empty.", request.getRequestURI(), SwinglabConst.AUTHORIZATION_HEADER);
            throw new SwinglabUnauthorizedException("Token 정보가 없습니다.");
        }

        // 3. Token 확인
        String token = jwtTokenProvider.getTokenFromHeader(header);
        if (!jwtTokenProvider.isValidToken(token))
        {
            log.error("{} : invalided token", request.getRequestURI());
            throw new SwinglabUnauthorizedException("유효하지 않은 Token입니다.");
        }

        // 4. 로그인아이디 확인
        String loginId = jwtTokenProvider.getLoginIdFromToken(token);
        if (StringUtil.isEmptyString(loginId))
        {
            log.error("{} : loginId in token is empty", request.getRequestURI());
            throw new SwinglabUnauthorizedException("유효하지 않은 Token입니다.");
        }

        //  SecurityContextHolder에 인증객체 세팅하기.
        SecurityUtil.setAuthentication(jwtTokenProvider.getAuthenticationFromToken(loginId));

        filterChain.doFilter(request, response);
    }
}
