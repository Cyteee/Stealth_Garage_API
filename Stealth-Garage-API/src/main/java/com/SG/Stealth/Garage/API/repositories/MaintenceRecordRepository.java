package com.SG.Stealth.Garage.API.repositories;

import com.SG.Stealth.Garage.API.entities.MaintenceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaintenceRecordRepository extends JpaRepository<MaintenceRecord, Long> {
    @Query("SELECT m FROM MaintenceRecord m WHERE m.km >= :quilometragem")
    List<MaintenceRecord> buscarManutencoesPorKmAcimaDe(@Param("quilometragem") Double quilometragem);

    @Query("SELECT p.usedPartsName, COUNT(m) FROM MaintenceRecord m JOIN m.parts p GROUP BY p.usedPartsName")
    List<Object[]> contarPecasMaisUtilizadas();
}
