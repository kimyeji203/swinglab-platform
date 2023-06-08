package com.dailystudy.swinglab.service.business.common.domain.entity.zone;

import com.dailystudy.swinglab.service.framework.core.gen.entity.UsageHistCore;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_usage_hist")
@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UsageHist extends UsageHistCore
{
}
