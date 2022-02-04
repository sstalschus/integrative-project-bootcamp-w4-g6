package com.mercadolibre.integrativeproject.services.interfaces;

public interface IAddressService<T, ID>{
    T create(T t);

    T getById(ID id);

    T update(T t);
}
