package com.mercadolibre.integrativeproject.services.interfaces;

import java.util.List;

/** Interface para InventaryRegisterService
 *
 * @author Samuel Stalschus
 *
 * */
public interface IInventaryRegister<T, J> {
    T create(T t);
    T findById(J id);
    List<T> getAll();
}
