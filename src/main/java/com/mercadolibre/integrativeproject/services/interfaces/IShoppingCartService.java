package com.mercadolibre.integrativeproject.services.interfaces;

import java.util.List;

/** Interface para ShoppingCartService
 *
 * @author Samuel Stalschus
 *
 * */
public interface IShoppingCartService<T, J, I> {
    double create(I i);
    T findById(J id);
    List<T> getAll();
}
