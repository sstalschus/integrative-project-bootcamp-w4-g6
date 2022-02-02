package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.repositories.BatchRepository;
import com.mercadolibre.integrativeproject.repositories.ProductRepository;
import com.mercadolibre.integrativeproject.services.interfaces.BathServiceInterface;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService implements ICrudServiceInterface<Batch, Long>, BathServiceInterface {

    private BatchRepository batchRepository;

    public BatchService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
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
}
