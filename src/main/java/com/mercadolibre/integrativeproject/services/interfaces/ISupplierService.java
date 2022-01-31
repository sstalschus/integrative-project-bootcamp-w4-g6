package com.mercadolibre.integrativeproject.services.interfaces;

import java.util.List;

public interface ISupplierService<T, ID> {

    T create(T t);

    T getById(ID id);

    List<T> getAll();

    void update(T t, ID id);

    void delete(ID id);
}
