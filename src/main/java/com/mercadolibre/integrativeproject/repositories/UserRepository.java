package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/** Repository do Usuário
 *
 * @author Arthur Amorim
 *
 * */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByName(String name);
}
