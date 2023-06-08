package com.dailystudy.swinglab.service.business.user.controller;

import com.dailystudy.swinglab.service.business.common.domain.entity.user.User;
import com.dailystudy.swinglab.service.business.user.service.UserService;
import com.dailystudy.swinglab.service.framework.http.response.PlatformResponseBuilder;
import com.dailystudy.swinglab.service.framework.http.response.domain.SuccessResponse;
import com.dailystudy.swinglab.service.framework.http.uris.UserUriConst;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @GetMapping(UserUriConst.GET_MY_INFO)
    public ResponseEntity<SuccessResponse<User>> getMyInfo ()
    {
        return PlatformResponseBuilder.build(userService.getUser());
    }
}
