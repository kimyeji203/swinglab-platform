package com.dailystudy.swinglab.service.business.repository.common;

import com.dailystudy.swinglab.service.business.domain.entity.common.ApiLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiLogRepository extends JpaRepository<ApiLog, String>
{
}
