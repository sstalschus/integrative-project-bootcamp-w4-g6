package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Storage;
import org.hibernate.id.insert.InsertGeneratedIdentifierDelegate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.GeneratedValue;
import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
//    public Optional<Property> findById(Long id);
}
