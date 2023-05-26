package com.dailystudy.swinglab.service.business.repository.zone;

import com.dailystudy.swinglab.service.business.domain.entity.zone.BookHist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookHistRepository extends JpaRepository<BookHist, Long>
{
}
