package com.dailystudy.swinglab.service.business.auth.controller;

import com.dailystudy.swinglab.service.business.auth.service.AuthService;
import com.dailystudy.swinglab.service.business.domain.entity.user.User;
import com.dailystudy.swinglab.service.framework.http.response.PlatformResponseBuilder;
import com.dailystudy.swinglab.service.framework.http.response.domain.SuccessResponse;
import com.dailystudy.swinglab.service.framework.http.uris.AuthUriConts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
