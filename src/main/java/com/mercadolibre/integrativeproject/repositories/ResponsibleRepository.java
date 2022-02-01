package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible,Long> {
    @Modifying
    @Query("update Responsible r set r.sector = ?1 where r.id = ?2")
    Responsible setResponsibleChangeSector(Long sector, Long id);
}
