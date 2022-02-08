package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.*;
import com.mercadolibre.integrativeproject.enums.StorageType;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.ProductRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ICrudServiceInterface<Product, Long> {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

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

        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) throw new NotFoundException("Products list not exist");
        return productList;
    }

    public List<Product> getByCategory(StorageType queryType){
        List<Product> productList = productRepository.findByCategory(queryType);
        if (productList.size() == 0) throw new NotFoundException("Product category not found");
        return productList;
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }
}
