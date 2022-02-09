package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Adverts;
import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Responsible;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.repositories.AdvertsRepository;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdvertsServiceTest {

    @Mock
    AdvertsRepository mockAdvertsRepository;

    @Mock
    SectorService mockSectorService;

    @Mock
    BatchService mockBatchService;

    @Mock
    ResponsibleService mockResponsibleService;

    @InjectMocks
    AdvertsService advertsService;

    @Test
    void create() {
        // Arrange
        Batch batch = new Batch();
        batch.setId(1L);

        Responsible responsible = Responsible.builder().id(1L).build();

        Adverts adverts = Adverts.builder().batch(batch).responsible(responsible).id(1L).build();
        Sector sector = Sector.builder().id(1L).lots(new ArrayList<>(Arrays.asList(batch))).build();
        List<Sector> sectorList = new ArrayList<>(Arrays.asList(sector));

        when(mockAdvertsRepository.save(adverts)).thenReturn(adverts);
        when(mockBatchService.getById(adverts.getBatch().getId())).thenReturn(batch);
        when(mockResponsibleService.getById(adverts.getResponsible().getId())).thenReturn(responsible);
        when(mockSectorService.getSectorByResponsible(1L)).thenReturn(sectorList);

        // Act
        Adverts advertsCreated = advertsService.create(adverts);

        // Assert
        assertEquals(adverts.getId(), advertsCreated.getId());
    }

    @Test
    void getSectorByResponsibleId() {
        // Arrange
        Sector sector = Sector.builder().id(1L).build();
        List<Sector> sectorList = new ArrayList<>(Arrays.asList(sector));

        when(mockSectorService.getSectorByResponsible(1L)).thenReturn(sectorList);

        // Act
        List<Sector> list = advertsService.getSectorByResponsibleId(1L);

        // Assert
        assertEquals(sectorList.size(), list.size());
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
    void getAll() {
        // Arrange
        Adverts adverts1 = Adverts.builder().id(1L).build();
        List<Adverts> advertsList = new ArrayList<>(Arrays.asList(adverts1));

        when(mockAdvertsRepository.findAll()).thenReturn(advertsList);

        // Act
        List<Adverts> list = advertsService.getAll();

        // Assert
        assertEquals(advertsList.size(), list.size());
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