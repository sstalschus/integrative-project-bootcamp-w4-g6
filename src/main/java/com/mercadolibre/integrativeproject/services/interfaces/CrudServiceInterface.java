package com.mercadolibre.integrativeproject.services.interfaces;

import java.util.List;

public interface CrudServiceInterface<T, ID> {

    T create(T t);

    T getById(ID id);
    List<T> getAll();

    T update(T t);

    void delete(ID t);
}
