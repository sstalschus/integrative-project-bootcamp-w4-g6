package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
