package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Repository de clientes
 *
 * @author Samuel Stalschus
 *
 * */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
