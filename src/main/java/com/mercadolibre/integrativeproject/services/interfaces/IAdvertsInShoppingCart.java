package com.mercadolibre.integrativeproject.services.interfaces;

import java.util.List;

/** Interface para AdvertsInShoppingCart
 *
 * @author Samuel Stalschus
 *
 * */
public interface IAdvertsInShoppingCart<T, J> {
    T create(T t);
    List<T> createMany(List<T> t);
    T findById(J id);
    List<T> getAllByShoppingCartId(J j);
}
