package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.*;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.SectorRepository;
import org.aspectj.weaver.ast.Not;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SectorServiceTest {

    @Mock
    SectorRepository mockSectorRepository;

    @Mock
    BatchService mockBatchService;

    @Mock
    StorageService mockStorageService;

    @Mock
    ResponsibleService mockResponsibleService;

    @InjectMocks
    SectorService sectorService;

    @Test
    void createSuccess() {
        // Arrange
        Storage storage = Storage.builder().sectorsList(new ArrayList<>()).id(2L).build();
        Responsible responsible = Responsible.builder().id(3L).build();
        Sector sector = Sector.builder().id(1L).storage(storage).responsible(responsible).build();

        when(mockStorageService.getById(sector.getStorage().getId())).thenReturn(storage);
        when(mockResponsibleService.getById(sector.getResponsible().getId())).thenReturn(responsible);
        when(mockSectorRepository.save(sector)).thenReturn(sector);
        when(mockResponsibleService.update(responsible)).thenReturn(responsible);

        // Act
        Sector sectorCreated = sectorService.create(sector);

        // Assert
        assertEquals(sector.getId(), sectorCreated.getId());

    }

    @Test
    void createError() throws NotFoundException {
        // Arrange
        Storage storage = Storage.builder().id(2L).build();
        Sector sector = Sector.builder().id(1L).storage(storage).build();

        when(mockStorageService.getById(sector.getStorage().getId())).thenReturn(null);

        try {
        // Act
            sectorService.create(sector);
            fail();
        }
        catch (NotFoundException e) {
        // Assert

            assertEquals("Storage not found", e.getMessage());
        }

    }

    @Test
    void getByIdSuccess() {
        // Arrange
        Sector sector = Sector.builder().id(1L).build();
        when(mockSectorRepository.findById(1L)).thenReturn(Optional.of(sector));

        // Act
        Sector sectorCreated = sectorService.getById(1L);

        // Assert
        assertEquals(sector.getId(), sectorCreated.getId());
    }

    @Test
    void getByIdError() throws NotFoundException {
        // Arrange
        when(mockSectorRepository.findById(1L)).thenThrow(new NotFoundException("Sector not found"));

        // Act and assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> sectorService.getById(1L));
        assertEquals("Sector not found", exception.getMessage());
    }

    @Test
    void getAll() {
        // Arrange
        Sector sector1 = Sector.builder().id(1L).build();
        Sector sector2 = Sector.builder().id(2L).build();

        List<Sector> list = new ArrayList<>(Arrays.asList(sector1, sector2));
        when(mockSectorRepository.findAll()).thenReturn(list);

        // Act
        List<Sector> listCreatd = sectorService.getAll();

        // Assert
        assertEquals(list.size(), listCreatd.size());
        assertEquals(list.get(0).getId(), listCreatd.get(0).getId());
    }

    @Test
    void update() {
        // Arrange
        Sector sector = Sector.builder().id(1L).build();

        when(mockSectorRepository.setSectorInfoById(sector.getCapacity(), sector.getTemperature(), sector.getName(), sector.getId())).thenReturn(sector);

        // Act
        Sector sectorCreated = sectorService.update(sector);

        // Assert
        assertEquals(sector.getId(), sectorCreated.getId());
    }

    @Test
    void getValidSectorOnStorageSuccess() {
        // Arrange
        Sector sector1 = Sector.builder().id(1L).build();
        Sector sector2 = Sector.builder().id(2L).build();
        List<Sector> list = new ArrayList<>(Arrays.asList(sector1, sector2));
        Storage storage = Storage.builder().sectorsList(list).id(3L).build();

        // Act
        Sector sector = sectorService.getValidSectorOnStorage(1L, storage);

        // Assert
        assertEquals(1L, sector.getId());
    }

    @Test
    void getValidSectorOnStorageError() throws NotFoundException {
        // Arrange
        Sector sector1 = Sector.builder().id(1L).build();
        Sector sector2 = Sector.builder().id(2L).build();
        List<Sector> list = new ArrayList<>(Arrays.asList(sector1, sector2));
        Storage storage = Storage.builder().sectorsList(list).id(3L).build();

        try {
            // Act
            sectorService.getValidSectorOnStorage(3L, storage);
        } catch (NotFoundException e) {
            // Assert
            assertEquals("Sector not found", e.getMessage());
        }
    }

    @Test
    void calcVolumn() {
        // Arrange
        Sector sector = Sector.builder().id(1L).build();

        when(mockBatchService.calcVolumn(sector.getLots())).thenReturn(10.0);

        // Act
        Double volumn = sectorService.calcVolumn(sector);

        // Assert
        assertEquals(10.0, volumn);
    }

    @Test
    void hasSectorCapacity() {
        // Arrange
        Sector sector = Sector.builder().capacity(100.0).id(1L).build();

        Batch batch1 = new Batch();
        batch1.setId(1L);

        List<Batch> batches = new ArrayList<>(Arrays.asList(batch1));

        when(mockBatchService.calcVolumn(sector.getLots())).thenReturn(10.0);
        when(mockBatchService.calcVolumn(batches)).thenReturn(15.0);

        // Act
        boolean hasSectorCapacity = sectorService.hasSectorCapacity(batches, sector);

        // Assert
        assertTrue(hasSectorCapacity);
    }

    @Test
    void notHasSectorCapacity() {
        // Arrange
        Sector sector = Sector.builder().capacity(13.0).id(1L).build();

        Batch batch1 = new Batch();
        batch1.setId(1L);

        List<Batch> batches = new ArrayList<>(Arrays.asList(batch1));

        when(mockBatchService.calcVolumn(sector.getLots())).thenReturn(10.0);
        when(mockBatchService.calcVolumn(batches)).thenReturn(15.0);

        // Act
        boolean hasSectorCapacity = sectorService.hasSectorCapacity(batches, sector);

        // Assert
        assertFalse(hasSectorCapacity);
    }

    @Test
    void listProductPerSectorOnAllStorage() {
        // Arrange
        Product product = Product.builder().id(1L).build();
        Batch batch1 = new Batch();
        batch1.setId(1L);
        batch1.setProduct(product);
        List<Batch> batches = new ArrayList<>(Arrays.asList(batch1));
        Sector sector1 = Sector.builder().lots(batches).capacity(13.0).id(1L).build();
        List<Sector> list = new ArrayList<>(Arrays.asList(sector1));
        Storage storage1 = Storage.builder().sectorsList(list).id(1L).build();
        List<Storage> storageList = new ArrayList<>(Arrays.asList(storage1));
        ProductPerStorage productPerStorage = new ProductPerStorage();

        when(mockStorageService.getAll()).thenReturn(storageList);

        // Act
        List<ProductPerStorage> productPerStorageList1 = sectorService.listProductPerSectorOnAllStorage(1L, "L");

        // Assert
        assertEquals(1L, productPerStorageList1.get(0).getStorage().getId());
    }

    @Test
    void getAmountProductPerStorage() {
        // Arrange
        Product product = Product.builder().id(1L).build();
        Batch batch1 = new Batch();
        batch1.setId(1L);
        batch1.setProduct(product);
        batch1.setInitialQuantity(10L);
        batch1.setQuantity(10L);

        List<Batch> batches = new ArrayList<>(Arrays.asList(batch1));
        Sector sector1 = Sector.builder().lots(batches).capacity(13.0).id(1L).build();
        List<Sector> list = new ArrayList<>(Arrays.asList(sector1));
        Storage storage1 = Storage.builder().sectorsList(list).id(1L).build();
        List<Storage> storageList = new ArrayList<>(Arrays.asList(storage1));
        ProductPerStorage productPerStorage = new ProductPerStorage();

        when(mockStorageService.getAll()).thenReturn(storageList);

        // Act
        List<AmountProductPerStorage> amountProductPerStorageList = sectorService.getAmountProductPerStorage(1L);

        // Assert
        assertEquals(batch1.getQuantity(), amountProductPerStorageList.get(0).getQuantity());
    }
}