package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Customer;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository mockCustomerRepository;

    @InjectMocks
    CustomerService customerService;

    @Test
    void create() {
        // Arrange
        Customer customer = Customer.builder().id(1L).build();

        when(mockCustomerRepository.save(customer)).thenReturn(customer);

        // Act
        Customer customerCreated = customerService.create(customer);

        // Assert
        assertEquals(customer.getId(), customerCreated.getId());
    }

    @Test
    void getByIdSuccess() {
        // Arrange
        Customer customer = Customer.builder().id(1L).build();
        when(mockCustomerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Act
        Customer customerCreated = customerService.getById(1L);

        // Assert
        assertEquals(customer.getId(), customerCreated.getId());
    }

    @Test
    void getByIdError() {
        // Arrange
        when(mockCustomerRepository.findById(1L)).thenThrow(new NotFoundException("Customer not found"));

        // Act and assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> customerService.getById(1L));
        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    void getAll() {
        // Arrange
        Customer customer1 = Customer.builder().id(1L).build();
        Customer customer2 = Customer.builder().id(2L).build();

        List<Customer> list = new ArrayList<>(Arrays.asList(customer1, customer2));
        when(mockCustomerRepository.findAll()).thenReturn(list);

        // Act
        List<Customer> listCreatd = customerService.getAll();

        // Assert
        assertEquals(list.size(), listCreatd.size());
        assertEquals(list.get(0).getId(), listCreatd.get(0).getId());
    }
}