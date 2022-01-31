package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible,Long> {
}
