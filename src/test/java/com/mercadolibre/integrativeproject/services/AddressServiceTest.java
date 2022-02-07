package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Address;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    AddressRepository mockAddressRepository;
    @InjectMocks
    AddressService addressService;

    @BeforeEach
    void init() {
//        addressService = new AddressService(mockAddressRepository);
    }

    @Test
    void create() {
        // Arrange
        Address address = Address.builder().id(1L).build();

        when(mockAddressRepository.saveAndFlush(address)).thenReturn(address);

        // Act
        Address addressCreated = addressService.create(address);

        // Assert
        assertEquals(address.getId(), addressCreated.getId());
    }

    @Test
    void getByIdSuccess() {
        // Arrange
        Address address = Address.builder().id(1L).build();
        when(mockAddressRepository.findById(1L)).thenReturn(Optional.of(address));

        // Act
        Address addressById = addressService.getById(1L);

        // Assert
        assertEquals(address.getId(), addressById.getId());
    }

    @Test
    void getByIdError() throws NotFoundException {
        // Arrange
        when(mockAddressRepository.findById(1L)).thenThrow(new NotFoundException("Address not Found"));

        // Act and assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> addressService.getById(1L));
        assertEquals("Address not Found", exception.getMessage());
    }

    @Test
    void updateSuccess() {
        // Arrange
        Address address = Address.builder().id(1L).build();
        when(mockAddressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(mockAddressRepository.save(address)).thenReturn(address);

        // Act
        Address addressCreated = addressService.update(address);

        // Assert
        assertEquals(address.getId(), addressCreated.getId());
    }

    @Test
    void updateError() throws NotFoundException {
        // Arrange
        Address address = Address.builder().id(1L).build();
        when(mockAddressRepository.findById(1L)).thenThrow(new NotFoundException("Address not Found"));

        // Act and assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> addressService.update(address));
        assertEquals("Address not Found", exception.getMessage());
    }
}