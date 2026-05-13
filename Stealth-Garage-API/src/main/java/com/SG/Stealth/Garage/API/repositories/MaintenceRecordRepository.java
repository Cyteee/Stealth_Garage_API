package com.SG.Stealth.Garage.API.repositories;

import com.SG.Stealth.Garage.API.entities.MaintenceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaintenceRecordRepository extends JpaRepository<MaintenceRecord, Long> {
    @Query("SELECT m FROM MaintenceRecord m WHERE m.cost >= :valorTeto")
    List<MaintenceRecord> buscarManutencoesAcimaDoTeto(@Param("valorTeto") Double valorTeto);

    @Query("SELECT p.name, COUNT(m) FROM MaintenceRecord m JOIN m.parts p GROUP BY p.name")
    List<Object[]> contarPecasMaisUtilizadas();
}
