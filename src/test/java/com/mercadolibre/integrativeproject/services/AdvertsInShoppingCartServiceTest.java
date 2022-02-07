package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Address;
import com.mercadolibre.integrativeproject.entities.AdvertsInShoppingCart;
import com.mercadolibre.integrativeproject.entities.ShoppingCart;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.AdvertsInShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
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

class AdvertsInShoppingCartServiceTest {

    @Mock
    AdvertsInShoppingCartRepository mockAdvertsInShoppingCartRepository;

    @Mock
    ShoppingCartService mockShoppingCartService;

    @InjectMocks
    AdvertsInShoppingCartService advertsInShoppingCartService;

    @Test
    void create() {
        // Arrange
        AdvertsInShoppingCart advertsInShoppingCart = AdvertsInShoppingCart.builder().id(1L).build();
        when(mockAdvertsInShoppingCartRepository.save(advertsInShoppingCart)).thenReturn(advertsInShoppingCart);

        // Act
        AdvertsInShoppingCart advertsInShoppingCartCreated = advertsInShoppingCartService.create(advertsInShoppingCart);

        // Assert
        assertEquals(advertsInShoppingCart.getId(), advertsInShoppingCartCreated.getId());
    }

    @Test
    void createMany() {
        // Arrange
        AdvertsInShoppingCart advertsInShoppingCart1 = AdvertsInShoppingCart.builder().id(1L).build();
        AdvertsInShoppingCart advertsInShoppingCart2 = AdvertsInShoppingCart.builder().id(2L).build();
        List<AdvertsInShoppingCart> list = new ArrayList<>(Arrays.asList(advertsInShoppingCart1, advertsInShoppingCart2));

        when(mockAdvertsInShoppingCartRepository.saveAllAndFlush(list)).thenReturn(list);

        // Act

        List<AdvertsInShoppingCart> listCreated = advertsInShoppingCartService.createMany(list);

        // Assert
        assertEquals(list.size(), listCreated.size());
        assertEquals(list.get(0).getId(), listCreated.get(0).getId());
    }

    @Test
    void findByIdSuccess() {
        // Arrange
        AdvertsInShoppingCart advertsInShoppingCart = AdvertsInShoppingCart.builder().id(1L).build();
        when(mockAdvertsInShoppingCartRepository.findById(1L)).thenReturn(Optional.of(advertsInShoppingCart));

        // Act
        AdvertsInShoppingCart advertsInShoppingCartCreated = advertsInShoppingCartService.findById(1L);

        // Assert
        assertEquals(advertsInShoppingCart.getId(), advertsInShoppingCartCreated.getId());
    }

    @Test
    void findByIdError() {
        // Arrange
        AdvertsInShoppingCart advertsInShoppingCart = AdvertsInShoppingCart.builder().id(1L).build();
        when(mockAdvertsInShoppingCartRepository.findById(1L)).thenThrow(new NotFoundException("AdvertsInShoppingCart not found"));

        // Act and assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> advertsInShoppingCartService.findById(1L));
        assertEquals("AdvertsInShoppingCart not found", exception.getMessage());
    }

    @Test
    void getAllByShoppingCartId() {
        // Arrange
        AdvertsInShoppingCart advertsInShoppingCart1 = AdvertsInShoppingCart.builder().id(1L).build();
        AdvertsInShoppingCart advertsInShoppingCart2 = AdvertsInShoppingCart.builder().id(2L).build();
        List<AdvertsInShoppingCart> list = new ArrayList<>(Arrays.asList(advertsInShoppingCart1, advertsInShoppingCart2));

        when(mockAdvertsInShoppingCartRepository.findByShoppingCarId(3L)).thenReturn(list);

        // Act
        List<AdvertsInShoppingCart> listCreated = advertsInShoppingCartService.getAllByShoppingCartId(3L);

        // Assert
        assertEquals(list.size(), listCreated.size());
        assertEquals(list.get(0).getId(), listCreated.get(0).getId());
    }
}