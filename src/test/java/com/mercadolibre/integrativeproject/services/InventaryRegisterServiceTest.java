package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.InventaryRegister;
import com.mercadolibre.integrativeproject.entities.Responsible;
import com.mercadolibre.integrativeproject.enums.TypeRegisterInventary;
import com.mercadolibre.integrativeproject.exceptions.MissingParamsException;
import com.mercadolibre.integrativeproject.repositories.InventaryRegisterRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
public class InventaryRegisterServiceTest {

    @Mock
    InventaryRegisterRepository mockInventaryRegisterRepository;

    @InjectMocks
    InventaryRegisterService inventaryRegisterService;

    @BeforeEach
    void init() {
//        inventaryRegisterService = new InventaryRegisterService(mockInventaryRegisterRepository);
    }

    @Test
    void createSuccess() {
        Batch batch = new Batch();
        batch.setId(2L);
        Responsible responsible = Responsible.builder().id(3L).build();

        InventaryRegister inventaryRegisterToSave = InventaryRegister.builder()
                .id(1L)
                .batch(batch)
                .responsible(responsible)
                .build();
        when(mockInventaryRegisterRepository.save(inventaryRegisterToSave)).thenReturn(inventaryRegisterToSave);
        InventaryRegister inventaryRegisterSaved = inventaryRegisterService.create(inventaryRegisterToSave);
        assertEquals(inventaryRegisterToSave.getId(), inventaryRegisterSaved.getId());
    }

    @Test
    void createErrorMissingParamsResponsible() throws MissingParamsException {
        Batch batch = new Batch();
        batch.setId(2L);

        InventaryRegister inventaryRegisterToSave = InventaryRegister.builder()
                .id(1L)
                .batch(batch)
                .build();
        MissingParamsException exception = assertThrows(MissingParamsException.class, () -> inventaryRegisterService.create(inventaryRegisterToSave));
        assertEquals("Responsible don't exists", exception.getMessage());
    }

    @Test
    void createErrorMissingParamsBatch() throws MissingParamsException {
        Responsible responsible = Responsible.builder().id(3L).build();

        InventaryRegister inventaryRegisterToSave = InventaryRegister.builder()
                .id(1L)
                .responsible(responsible)
                .build();
        MissingParamsException exception = assertThrows(MissingParamsException.class, () -> inventaryRegisterService.create(inventaryRegisterToSave));

        assertEquals("Batch don't exists", exception.getMessage());
    }

    @Test
    void findById() {

        Long id = 1L;

        InventaryRegister inventaryRegister = InventaryRegister.builder()
                .id(1L)
                .quantity(100)
                .type(TypeRegisterInventary.CREDIT)
                .build();

        when(mockInventaryRegisterRepository.findById(id)).thenReturn(Optional.of(inventaryRegister));

        InventaryRegister inventaryRegisterSaved = inventaryRegisterService.findById(id);

        assertEquals(id, inventaryRegisterSaved.getId());
    }

    @Test
    void getAll() {
        InventaryRegister inventaryRegister1 = InventaryRegister.builder()
                .id(1L)
                .quantity(100)
                .type(TypeRegisterInventary.CREDIT)
                .build();

        InventaryRegister inventaryRegister2 = InventaryRegister.builder()
                .id(1L)
                .quantity(100)
                .type(TypeRegisterInventary.CREDIT)
                .build();

        List<InventaryRegister> list = new ArrayList<>(Arrays.asList(inventaryRegister1, inventaryRegister2));

        when(mockInventaryRegisterRepository.findAll()).thenReturn(list);

        List<InventaryRegister> inventaryRegisters = inventaryRegisterService.getAll();

        assertEquals(list, inventaryRegisters);
    }
}
