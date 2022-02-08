package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Adverts;
import com.mercadolibre.integrativeproject.entities.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/** Repository do anúncios
 *
 * @author Daniel Ramos
 *
 * */
@Repository
public interface AdvertsRepository extends JpaRepository<Adverts, Long> {


}
