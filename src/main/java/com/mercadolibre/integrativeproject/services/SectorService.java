package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.*;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.SectorRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ISectorService;
import com.mercadolibre.integrativeproject.services.strategies.SortProductPerStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Service de Setor
 * @author Lorraine Mendes
 * */


@Service
public class SectorService implements ISectorService<Sector, Long> {

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private BatchService batchService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ResponsibleService responsibleService;

    @Override
    public Sector create(Sector sector) {
        Storage storage = storageService.getById(sector.getStorage().getId());
        if (storage != null) {
            Responsible responsible = responsibleService.getById(sector.getResponsible().getId());
            sector.setStorage(storage);
            sector.setResponsible(responsible);
            Sector sectorSaved = sectorRepository.save(sector);
            storage.getSectorsList().add(sectorSaved);
            responsibleService.update(responsible);
            return sectorSaved;
        }
        throw new NotFoundException("Storage not found");
    }

    @Override
    public Sector getById(Long sectorId) {
        return sectorRepository.findById(sectorId).orElseThrow(() -> new NotFoundException("Sector not found"));
    }

    @Override
    public List<Sector> getAll() {
            return sectorRepository.findAll();
    }

    @Override
    public void update(Sector sector) {
        sectorRepository.setSectorInfoById(sector.getTemperature(), sector.getCapacity(), sector.getName(), sector.getId());
    }

    @Override
    public void delete(Long sectorId) {
            sectorRepository.deleteById(sectorId);
    }

    public Sector getValidSectorOnStorage(Long id, Storage storage) {
        Sector sector = storage.getSectorsList().stream()
                .filter(sec -> sec.getId().equals(id))
                .findFirst().orElse(null);

        if (sector == null) {
            throw new NotFoundException("Sector not found");
        }
        return sector;
    }


    public Double calcVolumn(Sector sector) {
        return batchService.calcVolumn(sector.getLots());
    }

    public boolean hasSectorCapacity(List<Batch> batches, Sector sector) {
        Double volumnBatches = batchService.calcVolumn(batches);
        Double usedCapacitySector = calcVolumn(sector);
        Double newUsedCapacitySector = volumnBatches + usedCapacitySector;
        return sector.getCapacity() >= newUsedCapacitySector;
    }

    public List<ProductPerStorage> listProductPerSectorOnAllStorage(Long productId, String ordination) {
        List<Storage> allStorage = storageService.getAll();
        List<ProductPerStorage> productPerStorageList = new ArrayList<>();

        allStorage.forEach(storage -> {
            getSectorsWithProductIdOnStorage(productId, productPerStorageList, storage, ordination);
        });

        SortProductPerStorage.valueOf(ordination).sort(productPerStorageList);

        return productPerStorageList;
    }

    private void getSectorsWithProductIdOnStorage(Long productId, List<ProductPerStorage> productPerStorageList, Storage storage, String ordination) {
        List<ProductPerSector> productPerSectors = getProductPerSectors(productId, storage);
        if (!productPerSectors.isEmpty()) {
            ProductPerStorage productPerStorage = new ProductPerStorage(storage, productPerSectors);
            productPerStorageList.add(productPerStorage);
        }
    }

    private List<ProductPerSector> getProductPerSectors(Long productId, Storage storage) {
        List<ProductPerSector> productPerSectors = new ArrayList<>();
        storage.getSectorsList().forEach(sector -> {
            getBatchesWithProductId(productId, productPerSectors, sector);
        });
        return productPerSectors;
    }

    private void getBatchesWithProductId(Long productId, List<ProductPerSector> productPerSectors, Sector sector) {
        List<Batch> batches = sector.getLots().stream().filter(batch -> batch.getProduct().getId().equals(productId)).collect(Collectors.toList());
        if (!batches.isEmpty()) {
            ProductPerSector productPerSector = new ProductPerSector(sector, batches);
            productPerSectors.add(productPerSector);
        }
    }
}
