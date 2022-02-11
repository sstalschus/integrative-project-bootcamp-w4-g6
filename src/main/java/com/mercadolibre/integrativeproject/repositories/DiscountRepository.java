package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Repository de descontos, requisito 6 Samuel Stalschus
 *
 * @author Samuel Stalschus
 *
 * */
@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Modifying
    @Query("select d from Discount d where d.advert.batch.id = ?1")
    List<Discount> findByBatchId(Long id);

    @Modifying
    @Query("select d from Discount d where d.advert.id = ?1")
    List<Discount> findDiscountsByAdvertId(Long id);
}
