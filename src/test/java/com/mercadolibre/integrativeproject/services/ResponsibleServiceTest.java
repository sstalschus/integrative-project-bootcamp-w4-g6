package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Responsible;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.repositories.ResponsibleRepository;
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
public class ResponsibleServiceTest {

    @Mock
    ResponsibleRepository mockResponsibleRepository;

    @InjectMocks
    ResponsibleService responsibleService;

    @Test
    void create(){

        // Arrange
        Responsible responsible = Responsible.builder().id(1L).build();
        when(mockResponsibleRepository.save(responsible)).thenReturn(responsible);

        // Act
        Responsible responsibleCreate = responsibleService.create(responsible);

        // Assert
        assertEquals(responsible.getId(), responsibleCreate.getId());
    }

    @Test
    void getByIdSuccess(){

        // Arrange
        Responsible responsible = Responsible.builder().id(1L).build();
        when(mockResponsibleRepository.findById(1L)).thenReturn(Optional.of(responsible));

        // Act
        Responsible responsibleId = responsibleService.getById(1L);

        // Assert
        assertEquals(responsible.getId(),responsibleId.getId());
    }

    @Test
    void getByIdError() throws NoClassDefFoundError {

        // Arrange
        Responsible responsible = Responsible.builder().id(1L).build();
        when(mockResponsibleRepository.findById(1L)).thenThrow(new NotFoundException("Responsible not Found"));

        // Act and Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> responsibleService.getById(1L));
        assertEquals("Responsible not Found", exception.getMessage());
    }

    @Test
    void updateSuccess(){

        // Arrange
        Responsible responsible = Responsible.builder().id(1L).build();
        when(mockResponsibleRepository.findById(1L)).thenReturn(Optional.of(responsible));
        when(mockResponsibleRepository.save(responsible)).thenReturn(responsible);

        // Act
        Responsible responsibleCreate = responsibleService.update(responsible);

        // Assert
        assertEquals(responsible.getId(), responsibleCreate.getId());
    }

    @Test
    void updateError() throws NotFoundException {

        // Arrange
        Responsible responsible = Responsible.builder().id(1L).build();
        when(mockResponsibleRepository.findById(1L)).thenThrow(new NotFoundException("Responsible not Found"));

        // Act and assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> responsibleService.update(responsible));
        assertEquals("Responsible not Found", exception.getMessage());
    }

    @Test
    void getAll() {

        // Arrange
        Responsible responsible1 = Responsible.builder().id(1L).build();
        Responsible responsible2 = Responsible.builder().id(2L).build();
        ArrayList<Responsible> list = new ArrayList<>(Arrays.asList(responsible1, responsible2));

        when(mockResponsibleRepository.findAll()).thenReturn(list);

        // Act
        List<Responsible> listCreated = responsibleService.getAll();

        // Assert
        assertEquals(list.size(), listCreated.size());
        assertEquals(list.get(0).getId(), listCreated.get(0).getId());
    }

    @Test
    void deleteSuccess() throws RepositoryException{

        // Arrange
        Responsible responsible = Responsible.builder().id(1L).build();
        try{
            Mockito.doThrow(new RepositoryException("Error by delete Responsible")).when(mockResponsibleRepository).deleteById(1L);
            responsibleService.delete(1L);
            Assertions.fail();
        }catch(RepositoryException e){
        assertEquals("Error by delete Responsible", e.getMessage());
        }
    }
}



