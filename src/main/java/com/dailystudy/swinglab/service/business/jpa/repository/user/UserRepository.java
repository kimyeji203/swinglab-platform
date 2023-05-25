package com.dailystudy.swinglab.service.business.jpa.repository.user;

import com.dailystudy.swinglab.service.business.jpa.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>
{
    List<User> findAllByLoginIdAndDelYnFalse(String loginId);
    List<User> findAllByLoginIdAndUserIdNotAndDelYnFalse(String loginId, Long userSid);
}
