package com.dailystudy.swinglab.service.business.repository.zone;

import com.dailystudy.swinglab.service.business.domain.entity.zone.BookHist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookHistRepository extends JpaRepository<BookHist, Long>
{
    List<BookHist> findAllByZoneIdAndBookDayAndBookCnclYnIsFalse(Long zoneId, LocalDate bookDay);
}
