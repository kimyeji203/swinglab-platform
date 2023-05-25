package com.dailystudy.swinglab.service.business.jpa.repository.common;

import com.dailystudy.swinglab.service.business.jpa.entity.common.ApiLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

public interface ApiLogRepository extends JpaRepository<ApiLog, String>
{
}
