package com.mercadolibre.integrativeproject.services.interfaces;

import java.util.List;

public interface IPurchaseRecordService <T, ID> {

    T create(T t);

    T getById(ID id);
    List<T> getAll();

    void update(T t);

    void delete(ID t);
}
