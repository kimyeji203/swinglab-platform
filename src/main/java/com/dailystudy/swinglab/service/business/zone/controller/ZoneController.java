package com.dailystudy.swinglab.service.business.zone.controller;

import com.dailystudy.swinglab.service.business.common.domain.entity.zone.BookHist;
import com.dailystudy.swinglab.service.business.common.domain.entity.zone.SwingZone;
import com.dailystudy.swinglab.service.business.zone.service.ZoneService;
import com.dailystudy.swinglab.service.framework.http.response.PlatformResponseBuilder;
import com.dailystudy.swinglab.service.framework.http.response.domain.SuccessResponse;
import com.dailystudy.swinglab.service.framework.http.uris.ZoneUriConst;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 타석 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class ZoneController
{
    private final ZoneService zoneService;

    /**
     * 타석 목록 조회
     *
     * @return
     */
    @GetMapping(ZoneUriConst.GET_ZONE_LIST)
    public ResponseEntity<SuccessResponse<List<SwingZone>>> getZoneList ()
    {
        return PlatformResponseBuilder.build(zoneService.getZoneAllList());
    }

    /**
     * 타석 상세 조회
     *
     * @param zoneId
     * @return
     */
    @GetMapping(ZoneUriConst.GET_ZONE_DETAIL)
    public ResponseEntity<SuccessResponse<SwingZone>> getZoneDetail (@PathVariable("zoneId") Long zoneId)
    {
        return PlatformResponseBuilder.build(zoneService.getZoneDetail(zoneId));
    }

    /**
     * 타석 예약
     *
     * @param zonId
     * @param bookHist
     * @return
     */
    @PostMapping(ZoneUriConst.POST_ZONE_BOOK)
    public ResponseEntity<SuccessResponse<BookHist>> postZoneBook (@PathVariable("zoneId") Long zonId, @RequestBody BookHist bookHist)
    {
        return PlatformResponseBuilder.build(zoneService.bookAtZone(zonId, bookHist));
    }

    /**
     * 타석 예약 가능 여부 체크
     *
     * @param zonId
     * @param bookHist
     * @return
     */
    @PostMapping(ZoneUriConst.POST_ZONE_BOOKABLE_CHECK)
    public ResponseEntity<SuccessResponse<Boolean>> postZoneBookableCheck (@PathVariable("zoneId") Long zonId, @RequestBody BookHist bookHist)
    {
        zoneService.validateBookAtZone(zonId, bookHist);
        return PlatformResponseBuilder.build(true);
    }
}
