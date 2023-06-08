package com.dailystudy.swinglab.service.business.common.domain.entity.zone;

import com.dailystudy.swinglab.service.framework.core.gen.entity.BookHistCore;
import jakarta.persistence.Transient;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_book_hist")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BookHist extends BookHistCore
{
    @Transient
    private String zoneNm; // 타석 명
    @Transient
    private Boolean isMyBook; // 내 예약
}
