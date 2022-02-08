package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.InventaryRegister;
import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.enums.StorageType;
import com.mercadolibre.integrativeproject.enums.TypeRegisterInventary;
import com.mercadolibre.integrativeproject.repositories.InventaryRegisterRepository;
import com.mercadolibre.integrativeproject.repositories.ProductRepository;
import com.mercadolibre.integrativeproject.services.ProductService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Test
    void createSuccess() {
        String name = "PRODUCT 1";
        Product productBeforSave = getProductBeforSave(name);
        Product productAfterSave = getProductAfterSave(name, 1L);

        ProductRepository productRepositoryMock = getProductRepositoryMock();
        ProductService productServiceMock = getProductServiceMock(productRepositoryMock);

        when(productRepositoryMock.save(productBeforSave)).thenReturn(productAfterSave);

        Product product = productServiceMock.create(productBeforSave);

        assertEquals(productAfterSave, product);
    }

    @Test
    void createNameNotFound() {
        Product productBeforSave = getProductBeforSave(null);

        ProductRepository productRepositoryMock = getProductRepositoryMock();
        ProductService productServiceMock = getProductServiceMock(productRepositoryMock);

        Product product = productServiceMock.create(productBeforSave);

        assertNull(product);
    }

    @Test
    void getById() {
        Product expected = getProductAfterSave("PRODUCT TO GET BY ID", 1L);

        ProductRepository productRepositoryMock = getProductRepositoryMock();
        ProductService productServiceMock = getProductServiceMock(productRepositoryMock);

        when(productRepositoryMock.findById(1L)).thenReturn(java.util.Optional.of(expected));

        Product result = productServiceMock.getById(1L);

        assertEquals(expected, result);
    }

    @Test
    void getAll() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Produto 1");
        product1.setVolumn(10.0);
        product1.setCategory(StorageType.FF);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Produto 2");
        product2.setVolumn(10.0);
        product2.setCategory(StorageType.FF);

        List<Product> list = new ArrayList<>(Arrays.asList(product1, product2));
        ProductRepository productRepository = getProductRepositoryMock();
        ProductService productService = new ProductService(productRepository);
        when(productRepository.findAll()).thenReturn(list);

        List<Product> products = productService.getAll();

        assertEquals(list, products);
    }

    @Test
    void delete() {

        ProductRepository productRepositoryMock = getProductRepositoryMock();
        ProductService productServiceMock = getProductServiceMock(productRepositoryMock);

        ArgumentCaptor<Long> idToDeleteRepositoryMethod = ArgumentCaptor.forClass(Long.class);

        doNothing().when(productRepositoryMock).deleteById(idToDeleteRepositoryMethod.capture());
        productServiceMock.delete(1L);

        assertEquals(0L, idToDeleteRepositoryMethod.capture());
    }

    @Test
    void update() {
        Product original = getProductAfterSave("PRODUCT TO GET BY ID 1", 1L);
        Product forUpdate = getProductAfterSave("PRODUCT TO GET BY ID 2", 1L);

        ProductRepository productRepositoryMock = getProductRepositoryMock();
        ProductService productServiceMock = getProductServiceMock(productRepositoryMock);

        when(productRepositoryMock.save(forUpdate)).thenReturn(forUpdate);

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
        product.setCategory(StorageType.FF);
        return product;
    }

    private ProductRepository getProductRepositoryMock() {
        return mock(ProductRepository.class);
    }

    private ProductService getProductServiceMock(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }
}