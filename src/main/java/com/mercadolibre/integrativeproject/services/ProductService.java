package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.services.interfaces.CrudServiceInterface;

import java.util.List;

public class ProductService implements CrudServiceInterface<Product, Long> {
    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public Product getById(Long aLong) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void delete(Long t) {

    }
}
