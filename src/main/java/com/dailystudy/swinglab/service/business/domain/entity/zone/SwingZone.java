package com.dailystudy.swinglab.service.business.domain.entity.zone;

import com.dailystudy.swinglab.service.framework.core.gen.entity.SwingZoneCore;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_swing_zone")
@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SwingZone extends SwingZoneCore
{
}
