package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible,Long> {
//    @Modifying
//    @Query("UPDATE Responsible r SET r.sector.id = ?1 WHERE r.id = ?2")
//    void setResponsibleChangeSector(Long responsibleId, Long sectorId);
    //@Query(value = "update responsible r set r.sector_id = :sectorId where r.id = :responsibleId", nativeQuery = true)
    //void setResponsibleChangeSector(@Param("responsibleId") Long responsibleId, @Param("sectorId") Long sectorId);
}
