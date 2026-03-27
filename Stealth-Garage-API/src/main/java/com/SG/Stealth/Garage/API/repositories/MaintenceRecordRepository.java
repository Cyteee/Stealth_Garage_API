package com.SG.Stealth.Garage.API.repositories;

import com.SG.Stealth.Garage.API.entities.MaintenceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenceRecordRepository extends JpaRepository<MaintenceRecord, Long> {
}
