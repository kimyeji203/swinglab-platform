package com.dailystudy.swinglab.service.business.auth.controller;

import com.dailystudy.swinglab.service.business.auth.service.AuthService;
import com.dailystudy.swinglab.service.business.domain.JwtToken;
import com.dailystudy.swinglab.service.business.domain.entity.user.User;
import com.dailystudy.swinglab.service.framework.SwinglabConst;
import com.dailystudy.swinglab.service.framework.auth.JwtTokenProvider;
import com.dailystudy.swinglab.service.framework.http.response.PlatformResponseBuilder;
import com.dailystudy.swinglab.service.framework.http.response.domain.SuccessResponse;
import com.dailystudy.swinglab.service.framework.http.uris.AuthUriConts;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.utils.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController
{
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원가입
     *
     * @param user
     * @return
     */
    @PostMapping(AuthUriConts.POST_SIGNUP)
    public ResponseEntity<SuccessResponse<User>> postSignup (@RequestBody User user)
    {
        return PlatformResponseBuilder.build(authService.signupUser(user));
    }

    /**
     * 로그인
     *
     * @param user
     * @param response
     * @return
     */
    @PostMapping(AuthUriConts.POST_LOGIN)
    public ResponseEntity<SuccessResponse<JwtToken>> postLogin (@Valid @RequestBody User user, HttpServletResponse response)
    {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getLoginId(), new String(Base64.decodeBase64(user.getPwd())));
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        JwtToken result = jwtTokenProvider.generateJwtToken(user);
        response.addHeader(SwinglabConst.AUTHORIZATION_HEADER, StringUtils.join(SwinglabConst.BEARER_TOKEN, " ", result.getAccessToken()));
        return PlatformResponseBuilder.build(result);
    }
}
