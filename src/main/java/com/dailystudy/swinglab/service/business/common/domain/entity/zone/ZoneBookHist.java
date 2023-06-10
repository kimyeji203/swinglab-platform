package com.dailystudy.swinglab.service.business.common.domain.entity.zone;

import com.dailystudy.swinglab.service.framework.core.gen.entity.ZoneBookHistCore;
import jakarta.persistence.Transient;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_zone_book_hist")
@EqualsAndHashCode(callSuper = true)
public class ZoneBookHist extends ZoneBookHistCore
{
    @Transient
    private String zoneNm; // 타석 명
    @Transient
    private Boolean isMyBook; // 내 예약
}


