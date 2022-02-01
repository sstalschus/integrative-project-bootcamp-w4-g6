package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertsRepository extends JpaRepository<Batch, Long> {
}
