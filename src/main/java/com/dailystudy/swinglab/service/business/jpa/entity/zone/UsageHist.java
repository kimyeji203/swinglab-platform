package com.dailystudy.swinglab.service.business.jpa.entity.zone;

import com.dailystudy.swinglab.service.framework.jpa.gen.entity.UsageHistCore;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name="tb_usage_hist")
@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UsageHist extends UsageHistCore
{
}
