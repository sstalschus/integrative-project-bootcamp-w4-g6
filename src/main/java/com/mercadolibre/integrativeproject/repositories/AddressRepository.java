package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Repository do endereço
 *
 * @author Daniel Ramos
 *
 * */
@Repository
public interface AddressRepository  extends JpaRepository<Address, Long> {
}
