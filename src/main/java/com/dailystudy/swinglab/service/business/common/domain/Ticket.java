package com.dailystudy.swinglab.service.business.common.domain;

import com.dailystudy.swinglab.service.framework.SwinglabConst;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Ticket
{
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SwinglabConst.DAY_FORMAT, timezone = SwinglabConst.TIME_ZONE)
    private LocalDate svcStDay; // 서비스 시작일
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SwinglabConst.DAY_FORMAT, timezone = SwinglabConst.TIME_ZONE)
    private LocalDate svcEdDay; // 서비스 종료일
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = SwinglabConst.DAY_FORMAT, timezone = SwinglabConst.TIME_ZONE)
    private LocalDate svcRegDay; // 서비스 등록일
}
