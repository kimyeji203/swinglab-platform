package com.dailystudy.swinglab.service.framework.core.gen.entity;

import com.dailystudy.swinglab.service.framework.core.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;


@MappedSuperclass // 테이블로 생성되지 않도록 해주는 어노테이션
@EntityListeners(value = {AuditingEntityListener.class}) // AuditingEntityListener : JPA 내부에서 엔티티 객체가 생성/변경 되는것을 감지하는 역할
@Data
@EqualsAndHashCode(callSuper = true)
public class UsageHistCore extends BaseEntity
{
    @Id
    @Column(name = "BOOK_ID", nullable = false)
    private Long bookId;
    @Column(name = "CHK_IN_DT")
    private Date chkInDt;
    @Column(name = "CHK_OUT_DT")
    private Date chkOutDt;
    @Column(name = "AUTO_CHK_OUT_YN")
    private Boolean autoChkOutYn;
}