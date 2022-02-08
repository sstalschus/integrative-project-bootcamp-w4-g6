package com.mercadolibre.integrativeproject.services.interfaces;

/** Interface para Storage
 *
 * @author Daniel Ramos
 *
 * */
public interface IStorageService <T, ID>{
    T create(T t);

    T getById(ID id);
}
