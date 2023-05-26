package com.dailystudy.swinglab.service.business.domain.entity.zone;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BookDayInfo
{
    private Date bookDay;
    List<BookHist> bookList;
}
