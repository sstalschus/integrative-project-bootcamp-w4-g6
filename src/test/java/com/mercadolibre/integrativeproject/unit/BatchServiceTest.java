package com.mercadolibre.integrativeproject.unit;


import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.enums.CategoryProduct;
import com.mercadolibre.integrativeproject.repositories.BatchRepository;
import com.mercadolibre.integrativeproject.services.BatchService;
import com.mercadolibre.integrativeproject.services.SectorService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

class BatchServiceTest {

    @Test
    void create() {
        Product product = getProduct("Test Create Product");

        Batch batch1BeforeCreate = getBatchAfterCreate(product);

        Batch batch1AfterCreate = getBatchCreated(product, 1L);

        BatchRepository batchRepository = getBatchRepositoryMock();
        BatchService batchService = getBatchServiceMock(batchRepository);

        Mockito.when(batchRepository.save(batch1BeforeCreate)).thenReturn(batch1AfterCreate);
        Batch batchSaved = batchService.create(batch1BeforeCreate);

        assertEquals(batchSaved, batch1AfterCreate);
    }

    @Test
    void createAll() {
        Product product = getProduct("Test Create All Product");

        ArrayList<Batch> batchesBefore = getBatchWitchoutId(product);

        ArrayList<Batch> batchesAfter = getBatchesWitchId(product);


        BatchRepository batchRepository = getBatchRepositoryMock();
        BatchService batchService = getBatchServiceMock(batchRepository);

        Mockito.when(batchRepository.saveAll(batchesBefore)).thenReturn(batchesAfter);
        List<Batch> batchesSaved = batchService.create(batchesBefore);
        assertEquals(batchesSaved.get(0), batchesAfter.get(0));
        assertEquals(batchesSaved.get(1), batchesAfter.get(1));
    }

    @Test
    void getById() {
        Product product = getProduct("Test Find By Id Product");

        Batch resultGetById = getBatchCreated(product, 1L);

        BatchRepository batchRepository = getBatchRepositoryMock();
        BatchService batchService = getBatchServiceMock(batchRepository);

        Mockito.when(batchRepository.findById(1L)).thenReturn(Optional.of(resultGetById));
        Batch batch = batchService.getById(1L);

        assertEquals(batch, resultGetById);
    }

    @Test
    void getAll() {
        Product product = getProduct("Test findAll Product");

        ArrayList<Batch> batchesWitchId = getBatchesWitchId(product);

        BatchRepository batchRepositoryMock = getBatchRepositoryMock();
        BatchService batchServiceMock = getBatchServiceMock(batchRepositoryMock);

        Mockito.when(batchRepositoryMock.findAll()).thenReturn(batchesWitchId);

        List<Batch> all = batchServiceMock.getAll();
        assertEquals(2L, all.size());
    }

    @Test
    void update() {
        Product productBefore = getProduct("Test Update Product Before");
        Product productAfter = getProduct("Test Update Product After");

        Batch batchBefore = getBatchCreated(productBefore, 1L);
        Batch batchAfter = batchBefore.setProduct(productAfter);

        BatchRepository batchRepositoryMock = getBatchRepositoryMock();
        BatchService batchServiceMock = getBatchServiceMock(batchRepositoryMock);

        Mockito.when(batchRepositoryMock.save(batchAfter)).thenReturn(batchAfter);

        Batch updated = batchServiceMock.update(batchAfter);
        assertEquals(batchAfter, updated);
    }

    @Test
    void delete() {

        BatchRepository bathBatchRepositoryMock = getBatchRepositoryMock();
        BatchService batchServiceMock = getBatchServiceMock(bathBatchRepositoryMock);

        ArgumentCaptor<Long> idToDeleteRepositoryMethod = ArgumentCaptor.forClass(Long.class);

        doNothing().when(bathBatchRepositoryMock).deleteById(idToDeleteRepositoryMethod.capture());
        batchServiceMock.delete(1L);

        assertEquals(0L, idToDeleteRepositoryMethod.capture());
    }

    @Test
    void calcVolumn() {

        ArrayList<Batch> product_calcVolumn = getBatchesWitchId(getProduct("Product CalcVolumn", 10.0));
        BatchRepository batchRepositoryMock = getBatchRepositoryMock();
        BatchService batchServiceMock = getBatchServiceMock(batchRepositoryMock);
        Double totalVolumn = batchServiceMock.calcVolumn(product_calcVolumn);
        assertEquals(2000.0, totalVolumn);
    }



    private BatchService getBatchServiceMock(BatchRepository batchRepository) {
        return new BatchService(batchRepository, new SectorService());
    }

    private Product getProduct(String name) {
        Product product = new Product();
        product.setId(1L);
        product.setName(name);
        product.setVolumn(10.0);
        product.setCategory(CategoryProduct.FF);
        return product;
    }

    private Product getProduct(String name, Double volumn) {
        Product product = new Product();
        product.setId(1L);
        product.setName("name");
        product.setVolumn(volumn);
        return product;
    }

    private ArrayList<Batch> getBatchWitchoutId(Product product) {
        Batch batch1BeforeCreate = getBatchAfterCreate(product);

        Batch batch2BeforeCreate = getBatchAfterCreate(product);

        ArrayList<Batch> batchesBefore = new ArrayList<>();
        batchesBefore.add(0, batch1BeforeCreate);
        batchesBefore.add(1, batch2BeforeCreate);
        return batchesBefore;
    }

    private Batch getBatchAfterCreate(Product product) {
        Batch batch1BeforeCreate = new Batch();
        batch1BeforeCreate.setProduct(product);
        batch1BeforeCreate.setInitialQuantity(100L);
        batch1BeforeCreate.setQuantity(100L);
        batch1BeforeCreate.setCurrentTemperature(3.1);
        batch1BeforeCreate.setMinimumTemperature(4.0);
        batch1BeforeCreate.setBrand("Marca teste");
        batch1BeforeCreate.setExpirationDate(new Timestamp(1643727600000L));
        batch1BeforeCreate.setFabricationDate(new Timestamp(1609513200000L));
        batch1BeforeCreate.setPricePerUnit(new BigDecimal("10.2"));
        return batch1BeforeCreate;
    }

    private ArrayList<Batch> getBatchesWitchId(Product product) {
        Batch batch2AfterCreate = getBatchCreated(product, 2L);

        Batch batch1AfterCreate = getBatchCreated(product, 1L);
        ArrayList<Batch> batchesAfter = new ArrayList<>();
        batchesAfter.add(0, batch1AfterCreate);
        batchesAfter.add(1, batch2AfterCreate);
        return batchesAfter;
    }

    private Batch getBatchCreated(Product product, long l) {
        Batch resultGetById = new Batch();
        resultGetById.setId(l);
        resultGetById.setProduct(product);
        resultGetById.setInitialQuantity(100L);
        resultGetById.setQuantity(100L);
        resultGetById.setCurrentTemperature(3.1);
        resultGetById.setMinimumTemperature(4.0);
        resultGetById.setBrand("Marca teste");
        resultGetById.setExpirationDate(new Timestamp(1643727600000L));
        resultGetById.setFabricationDate(new Timestamp(1609513200000L));
        resultGetById.setPricePerUnit(new BigDecimal("10.2"));
        return resultGetById;
    }

    private BatchRepository getBatchRepositoryMock() {
        return Mockito.mock(BatchRepository.class);
    }

}