package com.dailystudy.swinglab.service.business.common.repository.zone;

import com.dailystudy.swinglab.service.business.common.domain.entity.zone.UsageHist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageHistRepository extends JpaRepository<UsageHist, Long>
{
}
