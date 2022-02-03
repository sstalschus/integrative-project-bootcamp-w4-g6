package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.repositories.BatchRepository;
import com.mercadolibre.integrativeproject.repositories.ProductRepository;
import com.mercadolibre.integrativeproject.services.interfaces.BathServiceInterface;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
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
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        Timestamp expiredDate = Timestamp.valueOf(now.toLocalDateTime().plusDays(numberOfDays));
        List<Batch> batchesFilterList = batches.stream().filter(batch -> batch.getExpirationDate().before(expiredDate)).collect(Collectors.toList());

        batchesFilterList.sort(Comparator.comparing(Batch::getExpirationDate));
        return batchesFilterList;

    }
}
