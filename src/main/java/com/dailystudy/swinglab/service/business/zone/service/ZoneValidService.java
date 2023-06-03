package com.dailystudy.swinglab.service.business.zone.service;

import com.dailystudy.swinglab.service.business.common.service.BaseService;
import com.dailystudy.swinglab.service.business.domain.entity.zone.BookHist;
import com.dailystudy.swinglab.service.business.domain.entity.zone.SwingZone;
import com.dailystudy.swinglab.service.business.repository.zone.BookHistRepository;
import com.dailystudy.swinglab.service.business.repository.zone.SwingZoneRepository;
import com.dailystudy.swinglab.service.framework.SwinglabConst;
import com.dailystudy.swinglab.service.framework.http.response.exception.http.SwinglabBadRequestException;
import com.dailystudy.swinglab.service.framework.http.response.exception.http.SwinglabNotFoundException;
import com.dailystudy.swinglab.service.framework.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ZoneValidService extends BaseService
{
    private final SwingZoneRepository swingZoneRepository;
    private final BookHistRepository bookHistRepository;

    /**
     * 존재하는 타석인지 확인 후 -> 리턴
     *
     * @param zoneSid
     * @return
     */
    public SwingZone getValidZone (Long zoneSid)
    {
        Optional<SwingZone> optional = swingZoneRepository.findById(zoneSid);
        if (optional.isPresent() == false)
        {
            throw new SwinglabNotFoundException("존재하지 않는 타석입니다.");
        }
        return optional.get();
    }

    /**
     * 해당 타석 예약 가능한지 체크
     *
     * @param bookHist
     */
    public void validateCanBook (Long zoneId, BookHist bookHist)
    {
        /*
         * 예약 시간 확인
         */
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate();
        LocalTime nowTime = now.toLocalTime().truncatedTo(ChronoUnit.MINUTES);
        bookHist.setBookStTime(bookHist.getBookStTime().truncatedTo(ChronoUnit.MINUTES));
        bookHist.setBookEdTime(bookHist.getBookEdTime().truncatedTo(ChronoUnit.MINUTES));
        // 예약일 확인 (과거인지)
        if (bookHist.getBookDay().isBefore(today))
        {
            throw new SwinglabBadRequestException(StringUtils.join(DateUtil.formatDate(today, SwinglabConst.DAY_FORMAT), " 이후 예약 가능합니다."));
        }
        // 시작시간 확인 (과거인지)
        if (bookHist.getBookStTime().isBefore(nowTime))
        {
            throw new SwinglabBadRequestException(StringUtils.join(DateUtil.formatDate(nowTime, SwinglabConst.TIME_FORMAT), " 이후 예약 가능합니다."));
        }
        // 시작과 끝 올바른지
        if (bookHist.getBookStTime().isAfter(bookHist.getBookEdTime()) || bookHist.getBookStTime().equals(bookHist.getBookEdTime()))
        {
            throw new SwinglabBadRequestException("예약 시작시간과 끝시간이 올바르지 않습니다.");
        }

        /*
         * 예약이 곂치는지 확인
         */
        // 예약 이력 조회
        List<BookHist> bookHistList = bookHistRepository.findAllByZoneIdAndBookDayAndBookCnclYnIsFalse(zoneId, bookHist.getBookDay());
        if (bookHistList.isEmpty())
        {
            return;
        }

        // 해당 시간 예약되었는지 확인
        LocalTime startTime = bookHist.getBookStTime();
        LocalTime endTime = bookHist.getBookEdTime();
        for (BookHist hist : bookHistList)
        {
            // 1. 예약하려는 시간안에 다른 예약이 이미 잡혀있는지.
            if (hist.getBookStTime().isBefore(endTime) && hist.getBookEdTime().isAfter(startTime))
            {
                throw new SwinglabBadRequestException("해당 시간은 이미 예약되었습니다.");
            }

            // 2. 다른예약과 곂치는지
            boolean includeStartTime = startTime.equals(hist.getBookStTime()) || (startTime.isAfter(hist.getBookStTime()) && startTime.isBefore(hist.getBookEdTime()));
            boolean includeEndTime = endTime.equals(hist.getBookEdTime()) || (endTime.isAfter(hist.getBookStTime()) && endTime.isBefore(hist.getBookEdTime()));
            if (includeStartTime || includeEndTime)
            {
                throw new SwinglabBadRequestException("해당 시간은 이미 예약되었습니다.");
            }
        }
    }
}
