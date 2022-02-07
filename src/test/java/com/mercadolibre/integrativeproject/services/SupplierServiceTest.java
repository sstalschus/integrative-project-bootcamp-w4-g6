package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Address;
import com.mercadolibre.integrativeproject.entities.Supplier;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.repositories.SupplierRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {

    @Mock
    SupplierRepository mockSupplierRepository;

    @Mock
    AddressService addressService;

    @InjectMocks
    SupplierService supplierService;

    @Test
    void create() {

        // Arrange
        Supplier supplier = Supplier.builder().id(1L)
                .address(Address.builder().id(1L).country("Brasil").city("São Paulo").state("SP")
                        .district("Mooca").street("Rua da Mooca 870").zipCode("03554000").build()).build();
                                when(addressService.create(supplier.getAddress())).thenReturn(supplier.getAddress());
                                when(mockSupplierRepository.save(supplier)).thenReturn(supplier);

        // Act
        Supplier supplierCreate = supplierService.create(supplier);

        // Assert
        assertEquals(supplier.getId(), supplierCreate.getId());
    }

    @Test
    void getByIdSuccess() {

        // Arrange
        Supplier supplier = Supplier.builder().id(1L).build();
        when(mockSupplierRepository.findById(1L)).thenReturn(Optional.of(supplier));

        // Act
        Supplier supplierId = supplierService.getById(1L);

        // Assert
        assertEquals(supplier.getId(), supplierId.getId());
    }

    @Test
    void getByIdError() throws NoClassDefFoundError {

        // Arrange
        Supplier supplier = Supplier.builder().id(1L).build();
        when(mockSupplierRepository.findById(1L)).thenThrow(new NotFoundException("Supplier not Found"));

        // Act and Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> supplierService.getById(1L));
        assertEquals("Supplier not Found", exception.getMessage());
    }

    @Test
    void updateSuccess() {

        // Arrange
        Supplier supplier = Supplier.builder().id(1L).build();
        when(mockSupplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        when(mockSupplierRepository.save(supplier)).thenReturn(supplier);

        // Act
        Supplier supplierCreate = supplierService.update(supplier);

        // Assert
        assertEquals(supplier.getId(), supplierCreate.getId());
    }

    @Test
    void updateError() throws NotFoundException {

        // Arrange
        Supplier supplier = Supplier.builder().id(1L).build();
        when(mockSupplierRepository.findById(1L)).thenThrow(new NotFoundException("Supplier not Found"));

        // Act and assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> supplierService.update(supplier));
        assertEquals("Supplier not Found", exception.getMessage());
    }

    @Test
    void getAll() {

        // Arrange
        Supplier supplier1 = Supplier.builder().id(1L).build();
        Supplier supplier2 = Supplier.builder().id(2L).build();
        ArrayList<Supplier> list = new ArrayList<>(Arrays.asList(supplier1, supplier2));

        when(mockSupplierRepository.findAll()).thenReturn(list);

        // Act
        List<Supplier> listCreated = supplierService.getAll();

        // Assert
        assertEquals(list.size(), listCreated.size());
        assertEquals(list.get(0).getId(), listCreated.get(0).getId());
    }

    @Test
    void deleteSuccess() throws RepositoryException {

        // Arrange
        Supplier supplier = Supplier.builder().id(1L).build();
        try {
            Mockito.doThrow(new RepositoryException("Error by delete Supplier")).when(mockSupplierRepository).deleteById(1L);
            supplierService.delete(1L);
            Assertions.fail();
        } catch (RepositoryException e) {
            assertEquals("Error by delete Supplier", e.getMessage());
        }
    }
}
