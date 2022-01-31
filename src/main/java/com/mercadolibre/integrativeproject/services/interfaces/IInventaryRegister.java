package com.mercadolibre.integrativeproject.services.interfaces;

import java.util.List;

/** Interface para InventaryRegisterService
 *
 * @author Samuel Stalschus
 *
 * */
public interface IInventaryRegister<T, ID> {
    T create(T t);
    T findById(ID id);
    List<T> getAll();
}
