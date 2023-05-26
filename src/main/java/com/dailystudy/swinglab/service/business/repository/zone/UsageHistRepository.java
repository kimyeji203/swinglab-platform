package com.dailystudy.swinglab.service.business.repository.zone;

import com.dailystudy.swinglab.service.business.domain.entity.zone.UsageHist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageHistRepository extends JpaRepository<UsageHist, Long>
{
}
