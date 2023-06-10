package com.dailystudy.swinglab.service.business.user.service;

import com.dailystudy.swinglab.service.business.common.domain.entity.user.Ticket;
import com.dailystudy.swinglab.service.business.common.repository.user.TicketRepository;
import com.dailystudy.swinglab.service.business.common.service.BaseService;
import com.dailystudy.swinglab.service.framework.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService extends BaseService
{
    private final TicketRepository ticketRepository;

    public Ticket getMyTicket()
    {
        Long userId = SecurityUtil.getUserId();
        return ticketRepository.findFirstByUserIdAndUseYnIsTrue(userId);
    }
}
