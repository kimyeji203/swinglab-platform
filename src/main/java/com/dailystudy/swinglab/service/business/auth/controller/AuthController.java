package com.dailystudy.swinglab.service.business.auth.controller;

import com.dailystudy.swinglab.service.business.auth.service.AuthService;
import com.dailystudy.swinglab.service.business.domain.JwtToken;
import com.dailystudy.swinglab.service.business.domain.entity.user.User;
import com.dailystudy.swinglab.service.framework.SwinglabConst;
import com.dailystudy.swinglab.service.framework.auth.TokenProvider;
import com.dailystudy.swinglab.service.framework.http.response.PlatformResponseBuilder;
import com.dailystudy.swinglab.service.framework.http.response.domain.SuccessResponse;
import com.dailystudy.swinglab.service.framework.http.uris.AuthUriConts;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

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
}
