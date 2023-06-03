package com.dailystudy.swinglab.service.framework.core.gen.entity;

import com.dailystudy.swinglab.service.framework.SwinglabConst;
import com.dailystudy.swinglab.service.framework.core.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SwinglabConst.DAY_FORMAT)
    @Column(name = "BOOK_DAY", nullable = false)
    private LocalDate bookDay;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SwinglabConst.TIME_FORMAT)
    @Column(name = "BOOK_ST_TIME", nullable = false)
    private LocalTime bookStTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SwinglabConst.TIME_FORMAT)
    @Column(name = "BOOK_ED_TIME", nullable = false)
    private LocalTime bookEdTime;
    @Column(name = "BOOK_CNCL_YN", nullable = false)
    private Boolean bookCnclYn;
}