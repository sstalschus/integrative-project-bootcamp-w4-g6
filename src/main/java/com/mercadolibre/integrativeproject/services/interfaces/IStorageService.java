package com.mercadolibre.integrativeproject.services.interfaces;

public interface IStorageService <T, ID>{
    T create(T t);

    T getById(ID id);
}
