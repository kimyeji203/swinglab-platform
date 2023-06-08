package com.dailystudy.swinglab.service.business.auth.service;

import com.dailystudy.swinglab.service.business.common.domain.entity.user.User;
import com.dailystudy.swinglab.service.business.common.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername (String loginId) throws UsernameNotFoundException
    {
        User user = userRepository.findByLoginIdAndDelYnFalse(loginId);
        if (user == null)
        {
            throw new UsernameNotFoundException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(StringUtils.join("ROLE_", "ALL")));
        return new org.springframework.security.core.userdetails.User(String.valueOf(user.getUserId()), user.getPwd(), authorities);
    }
}
