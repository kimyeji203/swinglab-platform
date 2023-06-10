package com.dailystudy.swinglab.service.business.common.repository.zone;

import com.dailystudy.swinglab.service.business.common.domain.entity.zone.ZoneBookHist;
import com.dailystudy.swinglab.service.framework.SwinglabConst;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.dailystudy.swinglab.service.business.common.domain.entity.zone.QZoneBookHist.zoneBookHist;

@Repository
@RequiredArgsConstructor
public class ZoneBookHistQueryRepository
{
    private final JPAQueryFactory jpaQueryFactory;

    public ZoneBookHist findOneByBookId (Long bookId)
    {
        return jpaQueryFactory
                .selectFrom(zoneBookHist)
                .where(zoneBookHist.bookId.eq(bookId))
                .fetchOne();
    }

    public List<ZoneBookHist> findAllByZoneIdAndBookDt (Long zoneId, LocalDateTime bookStDt, LocalDateTime bookEdDt)
    {
        return jpaQueryFactory
                .selectFrom(zoneBookHist)
                .where(zoneBookHist.zoneId.eq(zoneId)
                        , zoneBookHist.bookCnclYn.isFalse()
                        , zoneBookHist.bookEdDt.goe(bookStDt)
                        , zoneBookHist.bookStDt.loe(bookEdDt)
                ).fetch();
    }

    public ZoneBookHist findNextOneByUserId (Long userId)
    {
        return jpaQueryFactory
                .selectFrom(zoneBookHist)
                .where(zoneBookHist.userId.eq(userId)
                        , zoneBookHist.bookCnclYn.isFalse()
                        , zoneBookHist.bookStDt.goe(LocalDateTime.now().minusMinutes(SwinglabConst.DF_MIN))
                )
                .orderBy(zoneBookHist.bookStDt.asc())
                .fetchFirst();
    }

    public void updateBookCnclYnTrue(Long bookId)
    {
        jpaQueryFactory
                .update(zoneBookHist)
                .set(zoneBookHist.bookCnclYn, true)
                .set(zoneBookHist.updDt, LocalDateTime.now())
                .where(zoneBookHist.bookId.eq(bookId))
                .execute();
    }

}
