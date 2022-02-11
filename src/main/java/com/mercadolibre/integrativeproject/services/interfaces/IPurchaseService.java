package com.mercadolibre.integrativeproject.services.interfaces;

public interface IPurchaseService<T, ID>{
        T create(T t);

        T getById(ID id);

        T update(T t);

        void delete(ID t);
}
