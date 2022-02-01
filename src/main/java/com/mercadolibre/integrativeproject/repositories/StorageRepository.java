package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {

}
