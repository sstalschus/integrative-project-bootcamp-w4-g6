package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Purchase;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.repositories.PurchaseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceTest {

    @Mock
    PurchaseRepository mockPurchaseRepository;

    @InjectMocks
    PurchaseService purchaseService;

    @Test
    void create() {

        Purchase purchase = Purchase.builder().id(1L).build();
        when(mockPurchaseRepository.save(purchase)).thenReturn(purchase);

        // Act
        Purchase PurchaseCreate = purchaseService.update(purchase);

        // Assert
        assertEquals(purchase.getId(), PurchaseCreate.getId());

    }

    @Test
    void getByIdSuccess() {
        // Arrange
        Purchase purchase = Purchase.builder().id(1L).build();
        when(mockPurchaseRepository.findById(1L)).thenReturn(Optional.of(purchase));

        // Act
        Purchase purchaseId = purchaseService.getById(1L);

        // Assert
        assertEquals(purchase.getId(),purchaseId.getId());
    }

    @Test
    void getByIdError() throws NoClassDefFoundError {

        // Arrange
        Purchase purchase = Purchase.builder().id(1L).build();
        when(mockPurchaseRepository.findById(1L)).thenThrow(new NotFoundException("Purchase not Found"));

        // Act and Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> purchaseService.getById(1L));
    }

    @Test
    void getAll() {
        // Arrange
        Purchase purchase1 = Purchase.builder().id(1L).build();
        Purchase purchase2 = Purchase.builder().id(2L).build();
        ArrayList<Purchase> list = new ArrayList<>(Arrays.asList(purchase1, purchase2));

        when(mockPurchaseRepository.findAll()).thenReturn(list);

        // Act
        List<Purchase> listCreated = purchaseService.getAll();

        // Assert
        assertEquals(list.size(), listCreated.size());
        assertEquals(list.get(0).getId(), listCreated.get(0).getId());
    }

    @Test
    void update() {
        // Arrange
        Purchase purchase = Purchase.builder().id(1L).build();
        when(mockPurchaseRepository.save(purchase)).thenReturn(purchase);

        // Act
        Purchase PurchaseCreate = purchaseService.update(purchase);

        // Assert
        assertEquals(purchase.getId(), PurchaseCreate.getId());
    }

    @Test
    void delete() {
        // Arrange
        Purchase purchase = Purchase.builder().id(1L).build();
        try{
            Mockito.doThrow(new RepositoryException("Error by delete Purchase")).when(mockPurchaseRepository).deleteById(1L);
            purchaseService.delete(1L);
            Assertions.fail();
        }catch(RepositoryException e){}

    }

    @Test
    void changeStatus() {
        Purchase purchase = Purchase.builder().id(1L).build();
        when(mockPurchaseRepository.save(purchase)).thenReturn(purchase);

        // Act
        Purchase PurchaseCreate = purchaseService.update(purchase);

        // Assert
        assertEquals(purchase.getId(), PurchaseCreate.getId());
    }
}