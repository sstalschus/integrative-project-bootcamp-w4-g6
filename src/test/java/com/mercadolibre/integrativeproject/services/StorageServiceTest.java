package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Address;
import com.mercadolibre.integrativeproject.entities.Storage;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.StorageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StorageServiceTest {

    @Mock
    StorageRepository mockStorageRepository;

    @InjectMocks
    StorageService storageService;

    @Mock
    AddressService addressService;

    @Test
    void create() {

        // Arrange
        Storage storage = Storage.builder().id(1L)
                .address(Address.builder().id(1L).country("Brasil").city("São Paulo").state("SP")
                        .district("Mooca").street("Rua da Mooca 870").zipCode("03554000").build()).build();

        when(addressService.create(storage.getAddress())).thenReturn(storage.getAddress());
        when(mockStorageRepository.save(storage)).thenReturn(storage);

        // Act
        Storage storageCreate = storageService.create(storage);

        // Assert
        assertEquals(storage.getId(), storageCreate.getId());
    }

    @Test
    void getByIdSuccess() {

        // Arrange
        Storage storage = Storage.builder().id(1L).build();
        when(mockStorageRepository.findById(1L)).thenReturn(Optional.of(storage));

        // Act
        Storage storageId = storageService.getById(1L);

        // Assert
        assertEquals(storage.getId(), storageId.getId());
    }

    @Test
    void getByIdError() throws NoClassDefFoundError {

        // Arrange
        Storage storage = Storage.builder().id(1L).build();
        when(mockStorageRepository.findById(1L)).thenThrow(new NotFoundException("Storage not Found"));

        // Act and Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> storageService.getById(1L));
        assertEquals("Storage not Found", exception.getMessage());
    }

    @Test
    void getAll() {

        // Arrange
        Storage storage1 = Storage.builder().id(1L).build();
        Storage storage2 = Storage.builder().id(2L).build();
        ArrayList<Storage> list = new ArrayList<>(Arrays.asList(storage1, storage2));

        when(mockStorageRepository.findAll()).thenReturn(list);

        // Act
        List<Storage> listCreated = storageService.getAll();

        // Assert
        assertEquals(list.size(), listCreated.size());
        assertEquals(list.get(0).getId(), listCreated.get(0).getId());
    }

    @Test
    void getValidStorage(){

    }


}