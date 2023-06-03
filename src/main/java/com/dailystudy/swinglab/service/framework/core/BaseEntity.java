package com.dailystudy.swinglab.service.framework.core;

import com.dailystudy.swinglab.service.framework.SwinglabConst;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public class BaseEntity
{
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SwinglabConst.DT_FORMAT, timezone = SwinglabConst.TIME_ZONE)
    @CreatedDate
    @Column(name = "reg_dt")
	private LocalDateTime regDt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SwinglabConst.DT_FORMAT, timezone = SwinglabConst.TIME_ZONE)
    @LastModifiedDate
    @Column(name = "upd_Dt")
    private LocalDateTime updDt;
}
