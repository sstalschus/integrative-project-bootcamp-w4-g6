package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository  extends JpaRepository<Address, Long> {
//    public Optional<Property> findById(Long id);
}
