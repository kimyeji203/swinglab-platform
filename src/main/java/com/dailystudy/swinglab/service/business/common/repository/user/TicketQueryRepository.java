package com.dailystudy.swinglab.service.business.common.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.dailystudy.swinglab.service.business.common.domain.entity.user.QTicket.ticket;

@Repository
@RequiredArgsConstructor
public class TicketQueryRepository
{
    private final JPAQueryFactory jpaQueryFactory;

    public void updateOtherTicketByUserId(Long userId)
    {
        jpaQueryFactory.update(ticket)
                .set(ticket.useYn, false)
                .where(ticket.userId.eq(userId))
                .execute();

    }
}
