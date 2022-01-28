package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible,Long> {
    Optional<Responsible> findById(Long id);
}
