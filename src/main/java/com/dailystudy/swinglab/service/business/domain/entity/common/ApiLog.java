package com.dailystudy.swinglab.service.business.domain.entity.common;

import com.dailystudy.swinglab.service.framework.core.gen.entity.ApiLogCore;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="tb_api_log")
@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApiLog extends ApiLogCore
{
}



