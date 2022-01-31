package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.repositories.BatchRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BatchService implements ICrudServiceInterface<Batch, Long> {

    @Autowired
    private BatchRepository batchRepository;

    @Override
    public Batch create(Batch batch) {
        try {
            return batchRepository.save(batch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Batch> create(List<Batch> batches){
        List<Batch>  batchesSaveds = new ArrayList<>();
        batches.forEach(batch -> batchesSaveds.add(create(batch)));

        return batchesSaveds;
    }

    @Override
    public Batch getById(Long batchId) {
        try {
            return batchRepository.findById(batchId).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Batch> getAll() {
        try {
            return batchRepository.findAll();
        } catch (Exception e)  {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Batch update(Batch batch) {
        try {
            return batchRepository.save(batch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long batchId) {
        try {
            batchRepository.deleteById(batchId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Double calcVolumn(List<Batch> batches){
        return batches.stream().mapToDouble(batchOnSector ->
                batchOnSector.getQuantity() * batchOnSector.getProduct().getVolumn()).sum();
    }
}
