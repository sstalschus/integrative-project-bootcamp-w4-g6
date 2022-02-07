package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.PurchaseRecord;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.repositories.BatchRepository;
import com.mercadolibre.integrativeproject.repositories.PurchaseRecordRepository;
import com.mercadolibre.integrativeproject.repositories.ResponsibleRepository;
import com.mercadolibre.integrativeproject.repositories.SectorRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import com.mercadolibre.integrativeproject.services.interfaces.IPurchaseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseRecordService implements IPurchaseRecordService<PurchaseRecord, Long> {

    private PurchaseRecordRepository purchaseRecordRepository;

    public PurchaseRecordService(PurchaseRecordRepository purchaseRecordRepository) {
        this.purchaseRecordRepository = purchaseRecordRepository;
    }

    @Override
    public PurchaseRecord create(PurchaseRecord purchaseRecord){
        return purchaseRecordRepository.save(purchaseRecord);
    }

    @Override
    public PurchaseRecord getById(Long purchaseRecordId) throws NotFoundException {
        return purchaseRecordRepository.findById(purchaseRecordId).orElseThrow(()->new NotFoundException("Purchase record not found"));
    }

    @Override
    public List<PurchaseRecord> getAll() {
            return purchaseRecordRepository.findAll();
    }

    @Override
    public PurchaseRecord update(PurchaseRecord purchaseRecord) {
        return purchaseRecordRepository.setPucharseRecordInfoById(purchaseRecord.getQuantity(), purchaseRecord.getPrice(),purchaseRecord.getId());
    }

    @Override
    public void delete(Long purchaseRecordId) throws RepositoryException {
        try {
            purchaseRecordRepository.deleteById(purchaseRecordId);

        } catch (Exception e) {
            throw new RepositoryException("Error in delete purcharse.");
        }
    }
}
