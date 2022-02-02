package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.InventaryRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Repository de registro de inventário
 *
 * @author Samuel Stalschus
 *
 * */
@Repository
public interface InventaryRegisterRepository extends JpaRepository<InventaryRegister, Long> {
}
