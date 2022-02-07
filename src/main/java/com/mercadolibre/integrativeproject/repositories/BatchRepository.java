package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Repository do lote
 *
 * @author Arthur Amorim
 *
 * */
@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
}
