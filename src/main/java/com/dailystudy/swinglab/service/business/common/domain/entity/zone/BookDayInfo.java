package com.dailystudy.swinglab.service.business.common.domain.entity.zone;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookDayInfo
{
    private LocalDate bookDay;
    List<BookHist> bookList;
}
