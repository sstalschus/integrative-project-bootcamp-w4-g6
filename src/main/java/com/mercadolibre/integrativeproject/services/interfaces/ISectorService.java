package com.mercadolibre.integrativeproject.services.interfaces;

import java.util.List;

/** Interface para Sector
 *
 * @author Lorraine Mendes
 *
 * */
public interface ISectorService <T, ID> {
    T create(T t);

    T getById(ID id);
    List<T> getAll();

    void update(T t);

    void delete(ID t);

}
