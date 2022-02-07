package com.mercadolibre.integrativeproject.services.interfaces;

/** Interface para AddressService
 *
 * @author Jefferson Froes
 *
 * */
public interface IAddressService<T, ID>{
    T create(T t);

    T getById(ID id);

    T update(T t);
}
