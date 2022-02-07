package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.*;
import com.mercadolibre.integrativeproject.exceptions.MissingParamsException;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.exceptions.TemperatureException;
import com.mercadolibre.integrativeproject.repositories.InboundOrderRegisterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InboundServiceTest {

    @Mock
    InboundOrderRegisterRepository mockInboundOrderRegisterRepository;

    @Mock
    SectorService mockSectorService;

    @Mock
    BatchService mockBatchService;

    @Mock
    ProductService mockProductService;

    @Mock
    StorageService mockStorageService;

    @Mock
    ResponsibleService mockResponsibleService;

    @InjectMocks
    InboundService mockInboundService;

    @Test
    void createErrorMissingParamsExceptionBySection() throws MissingParamsException {
        // Arrange
        InboundOrder inboundOrder = InboundOrder.builder().orderNumber(1L).warehouseCode(2L).sectionCode(null).build();
        try{
        // Act
            mockInboundService.create(inboundOrder);
        } catch (MissingParamsException e){
            // Assert
            assertEquals("Missing params", e.getMessage());
        }
    }

    @Test
    void createErrorMissingParamsExceptionByOrder() throws MissingParamsException {
        // Arrange
        InboundOrder inboundOrder = InboundOrder.builder().orderNumber(null).warehouseCode(2L).sectionCode(3L).build();
        try{
        // Act
            mockInboundService.create(inboundOrder);
        } catch (MissingParamsException e){
            // Assert
            assertEquals("Missing params", e.getMessage());
        }
    }

    @Test
    void createErrorMissingParamsExceptionByWarehouse() throws MissingParamsException {
        // Arrange
        InboundOrder inboundOrder = InboundOrder.builder().orderNumber(1L).warehouseCode(null).sectionCode(3L).build();
        try{
        // Act
            mockInboundService.create(inboundOrder);
        } catch (MissingParamsException e){
            // Assert
            assertEquals("Missing params", e.getMessage());
        }
    }

    @Test
    void createErrorMissingParamsExceptionByResponsible() throws MissingParamsException {
        // Arrange
        Responsible responsible = Responsible.builder().id(null).build();
        InboundOrder inboundOrder = InboundOrder.builder().orderNumber(1L).warehouseCode(2L).sectionCode(3L).responsible(responsible).build();

        try{
        // Act
            mockInboundService.create(inboundOrder);
        } catch (MissingParamsException e){
            // Assert
            assertEquals("Missing params", e.getMessage());
        }
    }

    @Test
    void createErrorResponsibleNotBelongSector() throws RepositoryException {
        // Arrange
        Responsible responsible = Responsible.builder().id(6L).build();
        InboundOrder inboundOrder = InboundOrder.builder().orderNumber(1L).warehouseCode(2L).sectionCode(3L).responsible(responsible).build();

        Storage storage = Storage.builder().id(4L).build();
        Sector sector = Sector.builder().id(5L).build();

        Responsible responsibleNulable = new Responsible();

        when(mockStorageService.getValidStorage(inboundOrder.getWarehouseCode())).thenReturn(storage);
        when(mockSectorService.getValidSectorOnStorage(inboundOrder.getSectionCode(), storage)).thenReturn(sector);
        when(mockResponsibleService.getById(inboundOrder.getResponsible().getId())).thenReturn(responsibleNulable);

        try{
        // Act
        mockInboundService.create(inboundOrder);
        fail();
        } catch (RepositoryException e){
            // Assert
            assertEquals("Representante nao pertence ao setor informado", e.getMessage());
        }
    }

    @Test
    void createErrorOfCurrentTemperatureBatchInvalid() throws TemperatureException {
        // Arrange
        Responsible responsible = Responsible.builder().id(6L).build();
        Storage storage = Storage.builder().id(4L).build();
        Sector sector = Sector.builder().id(5L).responsible(responsible).build();

        Batch batch1 = new Batch();
        batch1.setId(7L);
        batch1.setMinimumTemperature(9.0);
        batch1.setCurrentTemperature(11.0);

        List<Batch> batches = new ArrayList<>(Arrays.asList(batch1));

        InboundOrder inboundOrder = InboundOrder.builder()
                .batches(batches)
                .orderNumber(1L)
                .warehouseCode(2L).sectionCode(3L).responsible(responsible).build();

        when(mockStorageService.getValidStorage(inboundOrder.getWarehouseCode())).thenReturn(storage);
        when(mockSectorService.getValidSectorOnStorage(inboundOrder.getSectionCode(), storage)).thenReturn(sector);
        when(mockResponsibleService.getById(inboundOrder.getResponsible().getId())).thenReturn(responsible);

        try{
        // Act
        mockInboundService.create(inboundOrder);
        fail();
        } catch (TemperatureException e){
            // Assert
            assertEquals("Current temperature of batch is not valid", e.getMessage());
        }
    }

    @Test
    void createErrorOfMinimunTemperatureBatchInvalid() throws TemperatureException {
        // Arrange
        Responsible responsible = Responsible.builder().id(6L).build();
        Storage storage = Storage.builder().id(4L).build();
        Sector sector = Sector.builder().id(5L).temperature(6.0).responsible(responsible).build();

        Batch batch1 = new Batch();
        batch1.setId(7L);
        batch1.setMinimumTemperature(9.0);
        batch1.setCurrentTemperature(8.0);

        List<Batch> batches = new ArrayList<>(Arrays.asList(batch1));

        InboundOrder inboundOrder = InboundOrder.builder()
                .batches(batches)
                .orderNumber(1L)
                .warehouseCode(2L).sectionCode(3L).responsible(responsible).build();

        when(mockStorageService.getValidStorage(inboundOrder.getWarehouseCode())).thenReturn(storage);
        when(mockSectorService.getValidSectorOnStorage(inboundOrder.getSectionCode(), storage)).thenReturn(sector);
        when(mockResponsibleService.getById(inboundOrder.getResponsible().getId())).thenReturn(responsible);

        try{
        // Act
        mockInboundService.create(inboundOrder);
        fail();
        } catch (TemperatureException e){
            // Assert
            assertEquals("Temperature of batch and sector not valid", e.getMessage());
        }
    }

    @Test
    void create() {
        // Arrange
        Responsible responsible = Responsible.builder().id(6L).build();
        Storage storage = Storage.builder().id(4L).build();
        Sector sector = Sector.builder().id(5L).temperature(14.0).lots(new ArrayList<>()).responsible(responsible).build();
        Product product = Product.builder().id(8L).build();

        Batch batch1 = new Batch();
        batch1.setId(7L);
        batch1.setMinimumTemperature(9.0);
        batch1.setCurrentTemperature(8.0);
        batch1.setProduct(product);
        batch1.setInitialQuantity(10L);

        List<Batch> batches = new ArrayList<>(Arrays.asList(batch1));

        InboundOrder inboundOrder = InboundOrder.builder()
                .batches(batches)
                .orderNumber(1L)
                .warehouseCode(2L).sectionCode(3L).responsible(responsible).build();


        when(mockStorageService.getValidStorage(inboundOrder.getWarehouseCode())).thenReturn(storage);
        when(mockSectorService.getValidSectorOnStorage(inboundOrder.getSectionCode(), storage)).thenReturn(sector);
        when(mockResponsibleService.getById(inboundOrder.getResponsible().getId())).thenReturn(responsible);
        when(mockProductService.getById(batch1.getProduct().getId())).thenReturn(product);
        when(mockSectorService.hasSectorCapacity(inboundOrder.getBatches(), sector)).thenReturn(true);
        when(mockBatchService.create(batch1)).thenReturn(batch1);

        // Act
            InboundOrder inboundOrderCreated = mockInboundService.create(inboundOrder);

        // Assert
            assertEquals(inboundOrder.getBatches().get(0).getQuantity(), inboundOrderCreated.getBatches().get(0).getQuantity());
            assertEquals(batch1, sector.getLots().get(0));

    }

    @Test
    void createErrorNotCapacity() throws NotFoundException {
        // Arrange
        Responsible responsible = Responsible.builder().id(6L).build();
        Storage storage = Storage.builder().id(4L).build();
        Sector sector = Sector.builder().id(5L).temperature(14.0).lots(new ArrayList<>()).responsible(responsible).build();
        Product product = Product.builder().id(8L).build();

        Batch batch1 = new Batch();
        batch1.setId(7L);
        batch1.setMinimumTemperature(9.0);
        batch1.setCurrentTemperature(8.0);
        batch1.setProduct(product);
        batch1.setInitialQuantity(10L);

        List<Batch> batches = new ArrayList<>(Arrays.asList(batch1));

        InboundOrder inboundOrder = InboundOrder.builder()
                .batches(batches)
                .orderNumber(1L)
                .warehouseCode(2L).sectionCode(3L).responsible(responsible).build();


        when(mockStorageService.getValidStorage(inboundOrder.getWarehouseCode())).thenReturn(storage);
        when(mockSectorService.getValidSectorOnStorage(inboundOrder.getSectionCode(), storage)).thenReturn(sector);
        when(mockResponsibleService.getById(inboundOrder.getResponsible().getId())).thenReturn(responsible);
        when(mockProductService.getById(batch1.getProduct().getId())).thenReturn(product);
        when(mockSectorService.hasSectorCapacity(inboundOrder.getBatches(), sector)).thenReturn(false);


        try {
            // Act
            mockInboundService.create(inboundOrder);
        } catch (NotFoundException e) {
            assertEquals("Setor nao possui capacidade para comportar este lotes", e.getMessage());
        }
    }

}