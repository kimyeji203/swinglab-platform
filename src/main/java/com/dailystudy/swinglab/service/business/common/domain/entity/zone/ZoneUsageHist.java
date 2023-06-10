package com.dailystudy.swinglab.service.business.common.domain.entity.zone;

import com.dailystudy.swinglab.service.framework.core.gen.entity.ZoneUsageHistCore;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name="tb_zone_usage_hist")
@EqualsAndHashCode(callSuper = true)
public class ZoneUsageHist extends ZoneUsageHistCore
{
}
