package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Repository de carrinho de compras
 *
 * @author Samuel Stalschus
 *
 * */
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
