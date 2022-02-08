package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Adverts;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.repositories.AdvertsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdvertsServiceTest {

    @Mock
    AdvertsRepository mockAdvertsRepository;

    @InjectMocks
    AdvertsService advertsService;

    @Test
    void create() {
        // Arrange
        Adverts adverts = Adverts.builder().id(1L).build();

        when(mockAdvertsRepository.save(adverts)).thenReturn(adverts);

        // Act
        Adverts advertsCreated = advertsService.create(adverts);

        // Assert
        assertEquals(adverts.getId(), advertsCreated.getId());
    }

    @Test
    void getByIdSuccess() {
        // Arrange
        Adverts adverts = Adverts.builder().id(1L).build();
        when(mockAdvertsRepository.findById(1L)).thenReturn(Optional.of(adverts));

        // Act
        Adverts advertsCreated = advertsService.getById(1L);

        // Assert
        assertEquals(adverts.getId(), advertsCreated.getId());
    }

    @Test
    void getByIdError() {


    }

    @Test
    void update() {
        // Arrange
        Adverts adverts = Adverts.builder().id(1L).build();

        when(mockAdvertsRepository.save(adverts)).thenReturn(adverts);

        // Act
        Adverts advertsCreated = advertsService.update(adverts);

        // Assert
        assertEquals(adverts.getId(), advertsCreated.getId());
    }

    @Test
    void deleteVoidError() throws RepositoryException {
        // Arrange
        Adverts adverts = Adverts.builder().id(1L).build();
        try{
            doThrow(new RepositoryException("Error by delete adverts")).when(mockAdvertsRepository).deleteById(1L);

            // Act
            advertsService.delete(1L);
            fail();
        } catch(RepositoryException e) {
            // Assert
            assertEquals("Error by delete adverts", e.getMessage());
        }
    }
}