package com.mercadolibre.integrativeproject.repositories;


import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/** Repository do Setor
 *
 * @author Lorraine Mendes
 *
 * */
@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
    @Modifying
    @Query("update Sector s set s.temperature = ?1, s.capacity = ?2, s.name = ?3 where s.id = ?4")
    Sector setSectorInfoById(Double temperature, Double capacity, String name, Long id);
}


