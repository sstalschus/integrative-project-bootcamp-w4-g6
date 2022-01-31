package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    @Modifying
    @Query("update Supplier s set s.name = ?1, s.cnpj = ?2 where s.id = ?3")
    void setSupplierInfoById(String name, String cnpj, Long userId);
}
