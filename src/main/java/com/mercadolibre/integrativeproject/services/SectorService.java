package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.*;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.SectorRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ISectorService;
import com.mercadolibre.integrativeproject.enums.SortProductPerStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Service de Setor
 * @author Lorraine Mendes, Arthur Mendes
 * */


@Service
public class SectorService implements ISectorService<Sector, Long> {

    private SectorRepository sectorRepository;

    private BatchService batchService;

    private StorageService storageService;

    private ResponsibleService responsibleService;

    public SectorService(SectorRepository sectorRepository, BatchService batchService, StorageService storageService, ResponsibleService responsibleService) {
        this.sectorRepository = sectorRepository;
        this.batchService = batchService;
        this.storageService = storageService;
        this.responsibleService = responsibleService;
    }

    public SectorService() {
    }

    /** Método usado para criar um novo setor
     *
     * @author Lorraine Mendes, Arthur Mendes
     *
     * @param  sector - Setor
     *
     * @return Setor
     *
     * @throws NotFoundException
     *
     * */
    @Override
    public Sector create(Sector sector) throws NotFoundException {
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

    /** Método usado para obter um Setor.
     *
     * @author Daniel Ramos, Samuel Stalschus
     *
     * @param  sectorId - Id do setor.
     *
     * @return Setor que tenha o sectorId informado
     *
     * @throws NotFoundException
     *
     * */
    @Override
    public Sector getById(Long sectorId) {
        return sectorRepository.findById(sectorId).orElseThrow(() -> new NotFoundException("Sector not found"));
    }

    /**
     * Método usado para pegar todos os registros de sector.
     *
     * @return Lista com os registros de sector.
     * @author Lorraine Mendes
     */
    @Override
    public List<Sector> getAll() {
            return sectorRepository.findAll();
    }

    /**
     * Método usado para atualizar o registro sector.
     *
     * @param sector - objeto que recebe os dados. O id do objeto a ser atualizado
     *
     * @author Lorraine Mendes.
     *
     */
    @Override
    public Sector update(Sector sector) {
        return sectorRepository.setSectorInfoById(sector.getTemperature(), sector.getCapacity(), sector.getName(), sector.getId());
    }

    /**
     * Método usado para pegar o lote do armazém.
     *
     * @param storage - objeto que valida o setor do armazém
     *
     * @return storage
     *
     * @author Lorraine Mendes, Arthur Amorim.
     *
     */
    public Sector getValidSectorOnStorage(Long id, Storage storage) throws NotFoundException {
        return storage.getSectorsList().stream()
                .filter(sec -> sec.getId().equals(id))
                .findFirst().orElseThrow(() -> new NotFoundException("Sector not found"));
    }

    /**
     * Método usado para calcular o volume.
     *
     * @param sector - objeto que valida o cálculo do volume
     *
     * @return calcVolumn
     *
     * @author Lorraine Mendes, Arthur Amorim.
     *
     */
    public Double calcVolumn(Sector sector) {
        return batchService.calcVolumn(sector.getLots());
    }

    /**
     * Método usado para calcular a capacidade do setor.
     *
     * @param sector - objeto que valida a capacidade do setor
     *
     * @return calcVolumn
     *
     * @author Lorraine Mendes.
     *
     */
    public boolean hasSectorCapacity(List<Batch> batches, Sector sector) {
        Double volumnBatches = batchService.calcVolumn(batches);
        Double usedCapacitySector = calcVolumn(sector);
        Double newUsedCapacitySector = volumnBatches + usedCapacitySector;
        return sector.getCapacity() >= newUsedCapacitySector;
    }

    /** Método usado para listar produto por setor e armazém
     *
     * @author Arthur Amorim, Jefferson Froes
     *
     * @return Lista com os produtos por setor e armazém
     *
     * */
    public List<ProductPerStorage> listProductPerSectorOnAllStorage(Long productId, String ordination) {
        List<Storage> allStorage = storageService.getAll();
        List<ProductPerStorage> productPerStorageList = new ArrayList<>();

        allStorage.forEach(storage -> {
            getSectorsWithProductIdOnStorage(productId, productPerStorageList, storage, ordination);
        });

        if(ordination != null) {
            SortProductPerStorage.valueOf(ordination).sort(productPerStorageList);
        }
        return productPerStorageList;
    }

    /** Método usado para obter o setor com o id do produto no armazém
     *
     * @author Arthur Amorim.
     *
     * */
    protected void getSectorsWithProductIdOnStorage(Long productId, List<ProductPerStorage> productPerStorageList, Storage storage, String ordination) {
        List<ProductPerSector> productPerSectors = getProductPerSectors(productId, storage);
        if (!productPerSectors.isEmpty()) {
            ProductPerStorage productPerStorage = new ProductPerStorage(storage, productPerSectors);
            productPerStorageList.add(productPerStorage);
        }
    }

    /** Método usado para obter o produto por setor
     *
     * @author Arthur Amorim.
     *
     * @return Lista com os produtos por setor
     *
     * */
    protected List<ProductPerSector> getProductPerSectors(Long productId, Storage storage) {
        List<ProductPerSector> productPerSectors = new ArrayList<>();
        storage.getSectorsList().forEach(sector -> {
            getBatchesWithProductId(productId, productPerSectors, sector);
        });
        return productPerSectors;
    }

    /** Método usado para obter o lote com o id do produto
     *
     * @author Arthur Amorim.
     *
     * */
    protected void getBatchesWithProductId(Long productId, List<ProductPerSector> productPerSectors, Sector sector) {
        List<Batch> batches = sector.getLots().stream().filter(batch -> batch.getProduct().getId().equals(productId)).collect(Collectors.toList());
        if (!batches.isEmpty()) {
            ProductPerSector productPerSector = new ProductPerSector(sector, batches);
            productPerSectors.add(productPerSector);
        }
    }

    /** Método usado para buscar uma lista de do total de todos os produtos em todos os armazéns.
     *
     * @author Jefferson Froes , Arthur Amorim
     * @param  productId - id do produto a ser buscado.
     * @return retorna o total de todos os produtos em estque.
     *
     * */
    public List<AmountProductPerStorage> getAmountProductPerStorage(Long productId){
        List<AmountProductPerStorage> amountProductPerStorages = new ArrayList<>();
        List<ProductPerStorage> productPerStorages = listProductPerSectorOnAllStorage(productId, null);
        Product product = new Product();
        product.setId(productId);
        productPerStorages.forEach(productPerStorage -> {
            Long amount = productPerStorage.getProductPerSectorList().stream().mapToLong(productPerSector ->
                productPerSector.getSector().getLots().stream().mapToLong(Batch::getQuantity).sum()
            ).sum();
            AmountProductPerStorage amountProductPerStorage = new AmountProductPerStorage();
            amountProductPerStorage.setStorage(productPerStorage.getStorage());
            amountProductPerStorage.setQuantity(amount);
            amountProductPerStorage.setProduct(product);
            amountProductPerStorages.add(amountProductPerStorage);
        });
        return amountProductPerStorages;
    }
}
