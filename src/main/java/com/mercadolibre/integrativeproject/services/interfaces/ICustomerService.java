package com.mercadolibre.integrativeproject.services.interfaces;

import java.util.List;

/** Interface para CustomerService
 *
 * @author Samuel Stalschus
 *
 * */
public interface ICustomerService<T, J> {
    T create(T t);
    T getById(J id);
    List<T> getAll();
}
