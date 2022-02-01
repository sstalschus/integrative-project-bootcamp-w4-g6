package com.mercadolibre.integrativeproject.unit;

import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.repositories.ProductRepository;
import com.mercadolibre.integrativeproject.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void create() {
        String name = "PRODUCT 1";
        Product productBeforSave = getProductBeforSave(name);
        Product productAfterSave = getProductAfterSave(name, 1L);

        ProductRepository productRepositoryMock = getProductRepositoryMock();
        ProductService productServiceMock = getProductServiceMock(productRepositoryMock);

        Mockito.when(productRepositoryMock.save(productBeforSave)).thenReturn(productAfterSave);

        Product product = productServiceMock.create(productBeforSave);

        assertEquals(productAfterSave, product);
    }

    @Test
    void getById() {
        Product expected = getProductAfterSave("PRODUCT TO GET BY ID", 1L);

        ProductRepository productRepositoryMock = getProductRepositoryMock();
        ProductService productServiceMock = getProductServiceMock(productRepositoryMock);

        Mockito.when(productRepositoryMock.findById(1L)).thenReturn(java.util.Optional.of(expected));

        Product result = productServiceMock.getById(1L);

        assertEquals(expected, result);
    }

    @Test
    void getAll() {
        Product product1 = getProductAfterSave("PRODUCT TO GET BY ID 1", 1L);
        Product product2 = getProductAfterSave("PRODUCT TO GET BY ID 2", 2L);

        List<Product> productsExpected = new ArrayList<>();
        productsExpected.add(product1);
        productsExpected.add(product2);

        ProductRepository productRepositoryMock = getProductRepositoryMock();
        ProductService productServiceMock = getProductServiceMock(productRepositoryMock);

        Mockito.when(productRepositoryMock.findAll()).thenReturn(productsExpected);

        List<Product> all = productServiceMock.getAll();

        assertEquals(2, all.size());
    }

    @Test
    void update() {
        Product original = getProductAfterSave("PRODUCT TO GET BY ID 1", 1L);
        Product forUpdate = getProductAfterSave("PRODUCT TO GET BY ID 2", 1L);

        ProductRepository productRepositoryMock = getProductRepositoryMock();
        ProductService productServiceMock = getProductServiceMock(productRepositoryMock);

        Mockito.when(productRepositoryMock.save(forUpdate)).thenReturn(forUpdate);

        Product result = productServiceMock.update(forUpdate);

        assertEquals("PRODUCT TO GET BY ID 2", result.getName());
        assertNotEquals(original.getName(), result.getName());

    }

    private Product getProductBeforSave(String name) {
        Product product = new Product();
        product.setName(name);
        product.setVolumn(10.0);
        return product;
    }

    private Product getProductAfterSave(String name, Long id) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setVolumn(10.0);
        return product;
    }

    private ProductRepository getProductRepositoryMock() {
        return Mockito.mock(ProductRepository.class);
    }

    private ProductService getProductServiceMock(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }
}