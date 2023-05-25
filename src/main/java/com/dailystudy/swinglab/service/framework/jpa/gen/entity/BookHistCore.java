package com.dailystudy.swinglab.service.framework.jpa.gen.entity;

import com.dailystudy.swinglab.service.framework.jpa.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.util.Date;


@MappedSuperclass // 테이블로 생성되지 않도록 해주는 어노테이션
@EntityListeners(value = {AuditingEntityListener.class}) // AuditingEntityListener : JPA 내부에서 엔티티 객체가 생성/변경 되는것을 감지하는 역할
@Data
@EqualsAndHashCode(callSuper = true)
public class BookHistCore extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID", nullable = false)
    private Long bookId;
    @Column(name = "USER_ID", nullable = false)
    private Long userId;
    @Column(name = "ZONE_ID", nullable = false)
    private Long zoneId;
    @Column(name = "BOOK_DAY", nullable = false)
    private Date bookDay;
    @Column(name = "BOOK_ST_TIME", nullable = false)
    private String bookStTime;
    @Column(name = "BOOK_ED_TIME", nullable = false)
    private String bookEdTime;
    @Column(name = "BOOK_CNCL_YN", nullable = false)
    private Boolean bookCnclYn;
}