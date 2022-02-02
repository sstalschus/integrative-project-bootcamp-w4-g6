package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.AdvertsInShoppingCart;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Repository de anuncios no carrinho de compras
 *
 * @author Samuel Stalschus
 *
 * */
@Repository
public interface AdvertsInShoppingCartRepository extends JpaRepository<AdvertsInShoppingCart, Long> {

    /** Método usado para obter todos os anuncios no carrinho de um determinado carrinho
     *
     * @author Samuel Stalschus
     *
     * @return Lista com todos os anuncios no carrinho de compras
     *
     * */
    @Modifying
    @Query("select a from AdvertsInShoppingCart a where a.shoppingCart = ?1")
    List<AdvertsInShoppingCart> findByShoppingCarId(Long shoppingCartId);
}
