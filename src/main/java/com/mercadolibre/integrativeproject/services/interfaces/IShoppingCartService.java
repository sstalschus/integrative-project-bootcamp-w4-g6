package com.mercadolibre.integrativeproject.services.interfaces;

import java.util.List;

/** Interface para ShoppingCartService
 *
 * @author Samuel Stalschus
 *
 * */
public interface IShoppingCartService<T, J, I, K> {
    double create(I i);
    T getById(J id);
    List<T> getAll();
    List<K> getProductsByShoppingCart(J id);
    void decrementProductByList(List<K> k);
}
