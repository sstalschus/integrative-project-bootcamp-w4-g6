package com.mercadolibre.integrativeproject.repositories;


import com.mercadolibre.integrativeproject.entities.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
}
