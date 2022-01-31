package com.mercadolibre.integrativeproject.unit;

import com.mercadolibre.integrativeproject.entities.InventaryRegister;
import com.mercadolibre.integrativeproject.enums.RegisterInventaryType;
import com.mercadolibre.integrativeproject.repositories.InventaryRegisterRepository;
import com.mercadolibre.integrativeproject.services.InventaryRegisterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventaryRegisterServiceTest {

    @Test
    void create() {
        InventaryRegister inventaryRegisterToSave = InventaryRegister.builder()
                .id(1L)
                .quantity(100)
                .type(RegisterInventaryType.CREDIT)
                .build();

        InventaryRegister inventaryRegister = InventaryRegister.builder()
                .id(1L)
                .quantity(100)
                .type(RegisterInventaryType.CREDIT)
                .build();


        InventaryRegisterRepository mockInventaryRegisterRepository = Mockito.mock(InventaryRegisterRepository.class);
        InventaryRegisterService inventaryRegisterService = new InventaryRegisterService(mockInventaryRegisterRepository);

        Mockito.when(mockInventaryRegisterRepository.save(inventaryRegisterToSave)).thenReturn(inventaryRegister);

        InventaryRegister inventaryRegisterSaved = inventaryRegisterService.create(inventaryRegisterToSave);

        assertEquals(inventaryRegisterSaved.getId(), inventaryRegisterToSave.getId());
    }

    @Test
    void findById() {

        Long id = 1L;

        InventaryRegister inventaryRegister = InventaryRegister.builder()
                .id(1L)
                .quantity(100)
                .type(RegisterInventaryType.CREDIT)
                .build();

        InventaryRegisterRepository mockInventaryRegisterRepository = Mockito.mock(InventaryRegisterRepository.class);
        InventaryRegisterService inventaryRegisterService = new InventaryRegisterService(mockInventaryRegisterRepository);

        Mockito.when(mockInventaryRegisterRepository.findById(id)).thenReturn(Optional.of(inventaryRegister));

        InventaryRegister inventaryRegisterSaved = inventaryRegisterService.getById(id);

        assertEquals(id, inventaryRegisterSaved.getId());
    }

    @Test
    void getAll() {
        InventaryRegister inventaryRegister1 = InventaryRegister.builder()
                .id(1L)
                .quantity(100)
                .type(RegisterInventaryType.CREDIT)
                .build();

        InventaryRegister inventaryRegister2 = InventaryRegister.builder()
                .id(1L)
                .quantity(100)
                .type(RegisterInventaryType.CREDIT)
                .build();

        List<InventaryRegister> list = new ArrayList<>(Arrays.asList(inventaryRegister1, inventaryRegister2));

        InventaryRegisterRepository mockInventaryRegisterRepository = Mockito.mock(InventaryRegisterRepository.class);
        InventaryRegisterService inventaryRegisterService = new InventaryRegisterService(mockInventaryRegisterRepository);

        Mockito.when(mockInventaryRegisterRepository.findAll()).thenReturn(list);

        List<InventaryRegister> inventaryRegisters = inventaryRegisterService.getAll();

        assertEquals(list, inventaryRegisters);
    }
}
