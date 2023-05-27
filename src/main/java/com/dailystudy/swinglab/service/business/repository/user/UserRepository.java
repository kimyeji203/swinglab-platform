package com.dailystudy.swinglab.service.business.repository.user;

import com.dailystudy.swinglab.service.business.domain.entity.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    List<User> findAllByLoginIdAndDelYnFalse(String loginId);
    List<User> findAllByLoginIdAndUserIdNotAndDelYnFalse(String loginId, Long userSid);

    User findByLoginIdAndDelYnFalse(String loginId);
}
