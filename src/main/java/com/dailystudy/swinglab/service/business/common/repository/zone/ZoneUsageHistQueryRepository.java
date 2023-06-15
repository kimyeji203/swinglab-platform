package com.dailystudy.swinglab.service.business.common.repository.zone;

import com.dailystudy.swinglab.service.business.common.domain.entity.zone.ZoneBookHist;
import com.dailystudy.swinglab.service.business.common.domain.entity.zone.ZoneUsageHist;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import static com.dailystudy.swinglab.service.business.common.domain.entity.zone.QZoneBookHist.zoneBookHist;
import static com.dailystudy.swinglab.service.business.common.domain.entity.zone.QZoneUsageHist.zoneUsageHist;

@Repository
@RequiredArgsConstructor
public class ZoneUsageHistQueryRepository
{
    private final JPAQueryFactory jpaQueryFactory;


    /**
     * pk로 단건 조회
     *
     * @param bookId
     * @return
     */
    public ZoneUsageHist findOneByKey (Long bookId)
    {
        return jpaQueryFactory
                .selectFrom(zoneUsageHist)
                .where(zoneUsageHist.bookId.eq(bookId))
                .fetchOne();
    }
}
