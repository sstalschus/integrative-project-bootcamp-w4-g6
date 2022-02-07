package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Adverts;
import com.mercadolibre.integrativeproject.entities.PurchaseRecord;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.repositories.PurchaseRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseRecordServiceTest {

    @Mock
    PurchaseRecordRepository mockPurchaseRecordRepository;

    @InjectMocks
    PurchaseRecordService purchaseRecordService;

    @Test
    void create() {
        // Arrange
        PurchaseRecord purcharse = PurchaseRecord.builder().id(1L).build();

        when(mockPurchaseRecordRepository.save(purcharse)).thenReturn(purcharse);

        // Act
        PurchaseRecord purcharseCreated = purchaseRecordService.create(purcharse);

        // Assert
        assertEquals(purcharse.getId(), purcharseCreated.getId());
    }

    @Test
    void getByIdSuccess() {
        // Arrange
        PurchaseRecord purcharse = PurchaseRecord.builder().id(1L).build();
        when(mockPurchaseRecordRepository.findById(1L)).thenReturn(Optional.of(purcharse));

        // Act
        PurchaseRecord purcharseCreated = purchaseRecordService.getById(1L);

        // Assert
        assertEquals(purcharse.getId(), purcharseCreated.getId());
    }

    @Test
    void getByIdError() throws NotFoundException {
        // Arrange
        when(mockPurchaseRecordRepository.findById(1L)).thenThrow(new NotFoundException("Purchase record not found"));

        // Act and assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> purchaseRecordService.getById(1L));
        assertEquals("Purchase record not found", exception.getMessage());
    }

    @Test
    void getAll() {
        // Arrange
        PurchaseRecord purcharse1 = PurchaseRecord.builder().id(1L).build();
        PurchaseRecord purcharse2 = PurchaseRecord.builder().id(2L).build();
        List<PurchaseRecord> list = new ArrayList<>(Arrays.asList(purcharse1, purcharse2));

        when(mockPurchaseRecordRepository.findAll()).thenReturn(list);

        // Act
        List<PurchaseRecord> listCreated = purchaseRecordService.getAll();

        // Assert

        assertEquals(list.size(), listCreated.size());
        assertEquals(list.get(0).getId(), listCreated.get(0).getId());
    }

    @Test
    void update() {
        // Arrange
        PurchaseRecord purcharse = PurchaseRecord.builder().quantity(1L).price(BigDecimal.valueOf(10.0)).id(1L).build();

        when(mockPurchaseRecordRepository.setPucharseRecordInfoById(purcharse.getQuantity(), purcharse.getPrice(), purcharse.getId()))
                .thenReturn(purcharse);

        // Act
        PurchaseRecord purcharseUpdated = purchaseRecordService.update(purcharse);

        // Assert
        assertEquals(purcharse.getId(), purcharseUpdated.getId());
    }

    @Test
    void deleteVoidError() throws RepositoryException {
        // Arrange
        Adverts adverts = Adverts.builder().id(1L).build();
        try{
            doThrow(new RepositoryException("Error in delete purcharse.")).when(mockPurchaseRecordRepository).deleteById(1L);

            // Act
            purchaseRecordService.delete(1L);
            fail();
        } catch(RepositoryException e) {
            // Assert
            assertEquals("Error in delete purcharse.", e.getMessage());
        }
    }
}