package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.PurchaseRecord;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
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

    @Autowired
    private PurchaseRecordRepository purchaseRecordRepository;


    @Override
    public PurchaseRecord create(PurchaseRecord purchaseRecord){
        try{
            return purchaseRecordRepository.save(purchaseRecord);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PurchaseRecord getById(Long purchaseRecordId) {
        return purchaseRecordRepository.findById(purchaseRecordId).orElseThrow(()->new NotFoundException("Purchase record not found"));
    }

    @Override
    public List<PurchaseRecord> getAll() {
        try {
            return purchaseRecordRepository.findAll();
        } catch (Exception e)  {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void update(PurchaseRecord purchaseRecord) {
        purchaseRecordRepository.setPucharseRecordInfoById(purchaseRecord.getQuantity(), purchaseRecord.getPrice(),purchaseRecord.getId());
    }

    @Override
    public void delete(Long purchaseRecordId) {
        try {
            purchaseRecordRepository.deleteById(purchaseRecordId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
