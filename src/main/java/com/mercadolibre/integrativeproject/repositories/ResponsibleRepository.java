package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Repository de registro de responsible
 *
 * @author Samuel Stalschus
 *
 * */
@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible,Long> {
}
