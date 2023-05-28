package com.dailystudy.swinglab.service.business.zone.service;

import com.dailystudy.swinglab.service.business.domain.entity.zone.BookDayInfo;
import com.dailystudy.swinglab.service.business.domain.entity.zone.SwingZone;
import com.dailystudy.swinglab.service.business.repository.zone.SwingZoneRepository;
import com.dailystudy.swinglab.service.framework.http.response.exception.http.SwinglabNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ZoneService
{
    private final SwingZoneRepository swingZoneRepository;

    /**
     * 타석 목록 조회
     *
     * @return
     */
    public List<SwingZone> getZoneAllList ()
    {
        Object obj = (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return swingZoneRepository.findAll(Sort.by(Sort.Direction.DESC, "regDt"));
    }

    /**
     * 타석 상세 조회
     *
     * @param zoneSid
     * @return
     */
    public SwingZone getZoneDetail (Long zoneSid)
    {
        Optional<SwingZone> optional = swingZoneRepository.findById(zoneSid);
        if (optional.isPresent() == false)
        {
            throw new SwinglabNotFoundException("존재하지 않는 타석입니다.");
        }
        // TODO 금일 예약 가능 여부
        // TODO 금일 내 예약 여부

        return optional.get();
    }

    public List<BookDayInfo> getBookDayInfoList (Date bookDaySt, Date bookDayEd)
    {
        bookDaySt = DateUtils.truncate(bookDaySt == null
                ? new Date()
                : bookDaySt, Calendar.DATE);
        bookDayEd = (bookDayEd == null || bookDaySt.after(bookDayEd))
                ? DateUtils.addDays(bookDaySt, 7)
                : DateUtils.truncate(bookDayEd, Calendar.DATE);
        return null;
    }
}
