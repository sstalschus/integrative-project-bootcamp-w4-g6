package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    Optional<Supplier> findById(Long id);
}
