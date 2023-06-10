package com.dailystudy.swinglab.service.business.common.repository.zone;

import com.dailystudy.swinglab.service.business.common.domain.entity.zone.ZoneBookHist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookHistRepository extends JpaRepository<ZoneBookHist, Long>
{
    List<ZoneBookHist> findAllByZoneIdAndBookDayAndBookCnclYnIsFalse(Long zoneId, LocalDate bookDay);
}
