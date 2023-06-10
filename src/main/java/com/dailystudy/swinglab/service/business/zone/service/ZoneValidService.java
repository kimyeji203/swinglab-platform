package com.dailystudy.swinglab.service.business.zone.service;

import com.dailystudy.swinglab.service.business.common.domain.entity.zone.Zone;
import com.dailystudy.swinglab.service.business.common.domain.entity.zone.ZoneBookHist;
import com.dailystudy.swinglab.service.business.common.repository.zone.ZoneBookHistQueryRepository;
import com.dailystudy.swinglab.service.business.common.repository.zone.ZoneBookHistRepository;
import com.dailystudy.swinglab.service.business.common.repository.zone.ZoneRepository;
import com.dailystudy.swinglab.service.business.common.service.BaseService;
import com.dailystudy.swinglab.service.framework.SwinglabConst;
import com.dailystudy.swinglab.service.framework.http.response.exception.http.SwinglabBadRequestException;
import com.dailystudy.swinglab.service.framework.http.response.exception.http.SwinglabNotFoundException;
import com.dailystudy.swinglab.service.framework.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ZoneValidService extends BaseService
{
    private final ZoneRepository zoneRepository;
    private final ZoneBookHistRepository zoneBookHistRepository;
    private final ZoneBookHistQueryRepository zoneBookHistQueryRepository;

    /**
     * 존재하는 타석인지 확인 후 -> 리턴
     *
     * @param zoneSid
     * @return
     */
    public Zone getValidZone (Long zoneSid)
    {
        Optional<Zone> optional = zoneRepository.findById(zoneSid);
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
    public void validateCanBook (Long zoneId, ZoneBookHist bookHist)
    {
        /*
         * 예약 시간 확인
         */
        LocalDateTime now = LocalDateTime.now();
        bookHist.setBookStDt(bookHist.getBookStDt().truncatedTo(ChronoUnit.MINUTES));
        bookHist.setBookEdDt(bookHist.getBookEdDt().truncatedTo(ChronoUnit.MINUTES));
        // 시작시간 확인 (과거인지)
        if (bookHist.getBookStDt().isBefore(now))
        {
            throw new SwinglabBadRequestException(StringUtils.join(DateUtil.formatDate(now, SwinglabConst.DT_FORMAT), " 이후 예약 가능합니다."));
        }
        // 시작과 끝 올바른지
        if (bookHist.getBookStDt().isAfter(bookHist.getBookEdDt()) || bookHist.getBookStDt().equals(bookHist.getBookEdDt()))
        {
            throw new SwinglabBadRequestException("예약 시간이 올바르지 않습니다.");
        }

        /*
         * 예약이 곂치는지 확인
         */
        // 예약 이력 조회
        List<ZoneBookHist> bookHistList = zoneBookHistQueryRepository.findAllByZoneIdAndBookStDt(zoneId, bookHist.getBookStDt());
        if (bookHistList.isEmpty())
        {
            return;
        }

        // 해당 시간 예약되었는지 확인
        LocalDateTime startTime = bookHist.getBookStDt();
        LocalDateTime endTime = bookHist.getBookEdDt();
        for (ZoneBookHist hist : bookHistList)
        {
            // 1. 예약하려는 시간안에 다른 예약이 이미 잡혀있는지.
            if (hist.getBookStDt().isBefore(endTime) && hist.getBookEdDt().isAfter(startTime))
            {
                throw new SwinglabBadRequestException("해당 시간은 이미 예약되었습니다.");
            }

            // 2. 다른예약과 곂치는지
            boolean includeStartTime = startTime.equals(hist.getBookStDt()) || (startTime.isAfter(hist.getBookStDt()) && startTime.isBefore(hist.getBookEdDt()));
            boolean includeEndTime = endTime.equals(hist.getBookEdDt()) || (endTime.isAfter(hist.getBookStDt()) && endTime.isBefore(hist.getBookEdDt()));
            if (includeStartTime || includeEndTime)
            {
                throw new SwinglabBadRequestException("해당 시간은 이미 예약되었습니다.");
            }
        }
    }
}
