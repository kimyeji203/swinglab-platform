package com.dailystudy.swinglab.service.business.jpa.entity.zone;

import com.dailystudy.swinglab.service.framework.jpa.gen.entity.BookHistCore;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name="tb_book_hist")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BookHist extends BookHistCore
{
    private String zoneNm; // 타석 명
    private Boolean isMyBook; // 내 예약
}
