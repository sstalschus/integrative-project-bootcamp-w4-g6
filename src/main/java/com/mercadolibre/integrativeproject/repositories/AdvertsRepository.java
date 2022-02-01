package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Adverts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertsRepository extends JpaRepository<Adverts, Long> {
}
