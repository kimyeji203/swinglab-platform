package com.dailystudy.swinglab.service.business.zone.service;

import com.dailystudy.swinglab.service.business.common.service.BaseService;
import com.dailystudy.swinglab.service.business.common.domain.entity.zone.ZoneBookHist;
import com.dailystudy.swinglab.service.business.common.domain.entity.zone.Zone;
import com.dailystudy.swinglab.service.business.common.repository.zone.BookHistRepository;
import com.dailystudy.swinglab.service.business.common.repository.zone.SwingZoneRepository;
import com.dailystudy.swinglab.service.framework.http.response.exception.http.SwinglabBadRequestException;
import com.dailystudy.swinglab.service.framework.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ZoneService extends BaseService
{
    private final ZoneValidService zoneValidService;

    private final SwingZoneRepository swingZoneRepository;
    private final BookHistRepository bookHistRepository;

    /**
     * 타석 목록 조회
     *
     * @return
     */
    public List<Zone> getZoneAllList ()
    {
        return swingZoneRepository.findAll(Sort.by(Sort.Direction.DESC, "regDt"));
    }

    /**
     * 타석 상세 조회
     *
     * @param zoneSid
     * @return
     */
    public Zone getZoneDetail (Long zoneSid)
    {
        this.assertNotEmpty(zoneSid, "타석 ID");

        // TODO 금일 예약 가능 여부
        // TODO 금일 내 예약 여부

        return zoneValidService.getValidZone(zoneSid);
    }


    /**
     * 해당 타석 예약
     *
     * @param zoneId
     * @param bookHist
     * @return
     */
    public ZoneBookHist bookAtZone (Long zoneId, ZoneBookHist bookHist)
    {
        Long userSid = SecurityUtil.getUserId();

        // 유효성 검사
        assertNotEmpty(bookHist, "예약정보");
        assertNotEmpty(zoneId, "타석 ID");
        assertNotEmpty(bookHist.getBookDay(), "예약일");
        assertNotEmpty(bookHist.getBookStTime(), "예약 시간(시작)");
        assertNotEmpty(bookHist.getBookEdTime(), "예약 시간(종료)");

        // 존재하는 타석인지 확인
        zoneValidService.getValidZone(zoneId);

        // 예약 가능한지 체크
        zoneValidService.validateCanBook(zoneId, bookHist);

        // 데이터 저장
        bookHist.setUserId(userSid);
        bookHist.setZoneId(zoneId);
        bookHist.setBookCnclYn(false);
        return bookHistRepository.save(bookHist);
    }

    /**
     * 예약 가능한지 체크
     *
     * @param zoneId
     * @param bookHist
     */
    public void validateBookAtZone (Long zoneId, ZoneBookHist bookHist)
    {
        try
        {
            // 유효성 검사
            assertNotEmpty(bookHist, "예약정보");
            assertNotEmpty(zoneId, "타석 ID");
            assertNotEmpty(bookHist.getBookDay(), "예약일");
            assertNotEmpty(bookHist.getBookStTime(), "예약 시간(시작)");
            assertNotEmpty(bookHist.getBookEdTime(), "예약 시간(종료)");

            // 존재하는 타석인지 확인
            zoneValidService.getValidZone(zoneId);

            // 예약 가능한지 체크
            zoneValidService.validateCanBook(zoneId, bookHist);
        } catch (Exception e)
        {
            throw new SwinglabBadRequestException("예약 할 수 없습니다.");
        }
    }


}
