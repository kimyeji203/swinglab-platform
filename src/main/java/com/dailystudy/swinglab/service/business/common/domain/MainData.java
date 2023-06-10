package com.dailystudy.swinglab.service.business.common.domain;

import com.dailystudy.swinglab.service.business.common.domain.entity.user.Ticket;
import com.dailystudy.swinglab.service.business.common.domain.entity.user.User;
import com.dailystudy.swinglab.service.business.common.domain.entity.zone.ZoneBookHist;
import lombok.Data;

/**
 * 메인 페이지 데이터
 */
@Data
public class MainData
{
    private User user; // 사용자 정보
    private Ticket ticket; // 이용권 정보
    private ZoneBookHist bookHist; // 예약 현황 최근 1건
}
