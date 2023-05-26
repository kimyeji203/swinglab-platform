package com.dailystudy.swinglab.service.business.common.controller;

import com.dailystudy.swinglab.service.business.domain.entity.user.User;
import com.dailystudy.swinglab.service.business.repository.user.UserRepository;
import com.dailystudy.swinglab.service.framework.http.response.PlatformResponseBuilder;
import com.dailystudy.swinglab.service.framework.http.response.domain.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController
{
    private final UserRepository userRepository;

    @GetMapping("/v1/test/{id}")
    public ResponseEntity<SuccessResponse<Integer>> getTestId(@PathVariable("id") Integer id) {
        return PlatformResponseBuilder.build(id);
    }

    @GetMapping("/v1/test/user")
    public ResponseEntity<SuccessResponse<List<User>>> getTestId(Pageable pageable) {
        return PlatformResponseBuilder.build(userRepository.findAll(pageable));
    }
}
