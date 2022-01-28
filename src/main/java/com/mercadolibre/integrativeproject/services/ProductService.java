package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.repositories.ProductRepository;
import com.mercadolibre.integrativeproject.services.interfaces.CrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements CrudServiceInterface<Product, Long> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            return null;
        }
        if (product.getId() != null && product.getId() > 0) {
            Product productOnDatabase = getById(product.getId());
            if (productOnDatabase != null) {
                if (product.getName().equals(productOnDatabase.getName())) {
                    return productOnDatabase;
                } else {
                    return null;
                }
            }
        } else {
            try {
                return productRepository.save(product);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    @Override
    public Product getById(Long productId) {

        try {
            return productRepository.findById(productId).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> getAll() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Product update(Product product) {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long productId) {
        try {
            productRepository.deleteById(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
