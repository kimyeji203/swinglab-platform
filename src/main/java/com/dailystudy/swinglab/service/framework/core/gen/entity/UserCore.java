package com.dailystudy.swinglab.service.framework.core.gen.entity;

import com.dailystudy.swinglab.service.framework.SwinglabConst;
import com.dailystudy.swinglab.service.framework.core.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@MappedSuperclass // 테이블로 생성되지 않도록 해주는 어노테이션
@EntityListeners(value = {AuditingEntityListener.class}) // AuditingEntityListener : JPA 내부에서 엔티티 객체가 생성/변경 되는것을 감지하는 역할
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCore extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;
    @Column(name = "LOGIN_ID", nullable = false)
    private String loginId;
    @Column(name = "PWD", nullable = false)
    private String pwd;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "NICK_NM")
    private String nickNm;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SwinglabConst.DT_FORMAT, timezone = SwinglabConst.TIME_ZONE)
    @Column(name = "SIGNUP_DT")
    private Date signupDt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SwinglabConst.DT_FORMAT, timezone = SwinglabConst.TIME_ZONE)
    @Column(name = "SVC_ST_DAY")
    private Date svcStDay;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SwinglabConst.DT_FORMAT, timezone = SwinglabConst.TIME_ZONE)
    @Column(name = "SVC_ED_DAY")
    private Date svcEdDay;
    @Column(name = "DEL_YN", nullable = false)
    private Boolean delYn;
}