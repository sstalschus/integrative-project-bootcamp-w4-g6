package com.mercadolibre.integrativeproject.services.interfaces;

import java.util.List;

public interface IResponsibleService<T, ID> {

    T create(T t, ID id);

    T getById(ID id);
    List<T> getAll();

    void update(ID id1, ID id2);

    void delete(ID t);
}
