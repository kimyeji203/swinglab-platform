package com.dailystudy.swinglab.service.business.common.service;

import com.dailystudy.swinglab.service.business.common.domain.MainData;
import com.dailystudy.swinglab.service.business.common.domain.entity.user.Ticket;
import com.dailystudy.swinglab.service.business.common.domain.entity.user.User;
import com.dailystudy.swinglab.service.business.common.domain.entity.zone.ZoneBookHist;
import com.dailystudy.swinglab.service.business.common.repository.zone.ZoneBookHistRepository;
import com.dailystudy.swinglab.service.business.user.service.TicketService;
import com.dailystudy.swinglab.service.business.user.service.UserValidationService;
import com.dailystudy.swinglab.service.framework.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainService extends BaseService
{
    private final UserValidationService userValidationService;
    private final TicketService ticketService;
    private final ZoneBookHistRepository zoneBookHistRepository;

    public MainData getMainData ()
    {
        Long userId = SecurityUtil.getUserId();
        User user = userValidationService.getValidUser(userId);
        Ticket ticket = ticketService.getMyTicket();
        ZoneBookHist bookHist = ZoneBookHist.builder().build();
        return new MainData(user, ticket, bookHist);
    }
}
