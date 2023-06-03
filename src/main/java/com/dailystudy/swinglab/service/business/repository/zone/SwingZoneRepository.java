package com.dailystudy.swinglab.service.business.repository.zone;

import com.dailystudy.swinglab.service.business.domain.entity.zone.SwingZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SwingZoneRepository extends JpaRepository<SwingZone, Long>
{
}
