package com.dailystudy.swinglab.service.business.common.repository.user;

import com.dailystudy.swinglab.service.business.common.domain.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.dailystudy.swinglab.service.business.common.domain.entity.user.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository
{
    private final JPAQueryFactory jpaQueryFactory;

    public User findOneByUserId (Long userId)
    {
        return jpaQueryFactory
                .selectFrom(user)
                .where(user.userId.eq(userId))
                .fetchOne();
    }

    public void updateTicketIdByUserId (Long userId, Long ticketId)
    {
        jpaQueryFactory
                .update(user)
                .set(user.ticketId, ticketId)
                .set(user.updDt, LocalDateTime.now())
                .where(user.userId.eq(userId))
                .execute();
    }
}
