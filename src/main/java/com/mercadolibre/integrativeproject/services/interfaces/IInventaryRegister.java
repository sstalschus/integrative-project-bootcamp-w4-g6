package com.mercadolibre.integrativeproject.services.interfaces;

import java.util.List;

public interface IInventaryRegister<T, ID> {
    T create(T t);
    T getById(ID id);
    List<T> getAll();
}
