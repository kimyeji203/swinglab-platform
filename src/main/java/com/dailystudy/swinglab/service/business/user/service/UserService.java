package com.dailystudy.swinglab.service.business.user.service;

import com.dailystudy.swinglab.service.business.common.service.BaseService;
import com.dailystudy.swinglab.service.business.domain.entity.user.User;
import com.dailystudy.swinglab.service.business.repository.user.UserRepository;
import com.dailystudy.swinglab.service.framework.http.response.exception.http.SwinglabNotFoundException;
import com.dailystudy.swinglab.service.framework.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService extends BaseService
{
    private final UserRepository userRepository;

    /**
     *
     * @return
     * @throws AuthenticationException
     */
    public User getUser ()
    {
        Long userId = SecurityUtil.getUserId();
        User user = userRepository.findByUserIdAndDelYnFalse(userId);
        if (user == null)
        {
            throw new SwinglabNotFoundException("사용자 정보를 찾을 수 없습니다.");
        }
        return user;
    }
}
