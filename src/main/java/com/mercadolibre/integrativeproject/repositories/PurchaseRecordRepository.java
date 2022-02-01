package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.PurchaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;

@Repository
public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {
    @Modifying
    @Query("update PurchaseRecord s set s.quantity = ?1, s.price = ?2 where s.id = ?3")
    void setPucharseRecordInfoById(Long quantity, BigDecimal price, Long userId);

}
