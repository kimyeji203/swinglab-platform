package com.dailystudy.swinglab.service.business.jpa.repository.zone;

import com.dailystudy.swinglab.service.business.jpa.entity.zone.BookHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

public interface BookHistRepository extends JpaRepository<BookHist, Long>
{
}
