package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.PurchaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {

}
