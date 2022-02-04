package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.enums.StorageType;
import com.mercadolibre.integrativeproject.repositories.BatchRepository;
import com.mercadolibre.integrativeproject.services.interfaces.BathServiceInterface;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchService implements ICrudServiceInterface<Batch, Long>, BathServiceInterface {

    private BatchRepository batchRepository;

    private SectorService sectorService;

    public BatchService(BatchRepository batchRepository, @Lazy SectorService sectorService) {
        this.batchRepository = batchRepository;
        this.sectorService = sectorService;
    }

    @Override
    public Batch create(Batch batch) {
        return batchRepository.save(batch);
    }

    public List<Batch> create(List<Batch> batches){
        return batchRepository.saveAll(batches);
    }

    @Override
    public Batch getById(Long batchId) {
        return batchRepository.findById(batchId).orElse(null);
    }

    @Override
    public List<Batch> getAll() {
        return batchRepository.findAll();
    }

    @Override
    public Batch update(Batch batch) {
        return batchRepository.save(batch);
    }

    @Override
    public void delete(Long batchId) {
        batchRepository.deleteById(batchId);
    }

    @Override
    public Double calcVolumn(List<Batch> batches) {
        return batches.stream().mapToDouble(batchOnSector ->
                batchOnSector.getQuantity() * batchOnSector.getProduct().getVolumn()).sum();
    }

    public List<Batch> getBatchesByDueDate(Integer numberOfDays, Long sectorId){
        Sector sector = sectorService.getById(sectorId);
        List<Batch> batches = sector.getLots();

        List<Batch> batchesFilterList = filterBacthsByDueDate(numberOfDays, batches);

        batchesFilterList.sort(Comparator.comparing(Batch::getExpirationDate));
        return batchesFilterList;
    }

    /** Método usado para obter uma lista dos lotes pedidos que vão expirar em uma determinada quantidade de dias de uma determinada categoria de produtos
     *
     * @author Samuel Stalschus
     *
     * @param numberOfDays - numero de dias dentro do range de expiração de produto
     * @param category - categoria do produto
     * @param asc - ordenar lista de lotes em ordem ascedente
     *
     * @return Lista de lotes com base na data de validade e categoria
     *
     * */
    public List<Batch> getBatchesByProductCategoyAndDueDate(Integer numberOfDays, StorageType category, boolean asc){
        List<Batch> batchs = batchRepository.findAll()
                .stream()
                .filter(batch -> batch.getProduct().getCategory().equals(category))
                .collect(Collectors.toList());

        batchs = filterBacthsByDueDate(numberOfDays, batchs);

        if(asc) batchs.sort(Comparator.comparing(Batch::getExpirationDate));
        else batchs.sort(Comparator.comparing(Batch::getExpirationDate).reversed());

        return  batchs;
    }

    /** Método usado para obter uma lista de lotes e filtrar com base na data de expiração desejada pelo client
     *
     * @author Samuel Stalschus
     *
     * @param numberOfDays - numero de dias dentro do range de expiração de produto
     * @param batches - categoria do produto
     *
     * @return Lista de lotes que estão com a data de expiração dentro do range de dias informados
     *
     * */
    private List<Batch> filterBacthsByDueDate(Integer numberOfDays, List<Batch> batches) {
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        Timestamp expiredDate = Timestamp.valueOf(now.toLocalDateTime().plusDays(numberOfDays));
        return batches.stream().filter(batch -> batch.getExpirationDate().before(expiredDate)).collect(Collectors.toList());
    }
}
