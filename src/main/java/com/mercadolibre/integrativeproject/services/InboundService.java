package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.*;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.InboundOrderRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/** Inbound Service
 * @author Arthur Amorim
 * @author Lorraine Mendes
 * */

@Service
public class InboundService {

    @Autowired
    private InboundOrderRegisterRepository inboundOrderRegisterRepository;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private BatchService batchService;

    @Autowired
    private StorageService storageService;

    public InboundOrder create(InboundOrder inboundOrder){
        Storage storage = storageService.getValidStorage(inboundOrder.getWarehouseCode());

        Sector sector = sectorService.getValidSectorOnStorage(inboundOrder.getSectionCode(), storage);

        verifyTemperatureInboundOrder(inboundOrder, sector);


        if (sectorService.hasSectorCapacity(inboundOrder.getBatches(), sector)){
            inboundOrder.getBatches().forEach(batch -> {
                batchService.create(batch);
                registerBatchPurchase(inboundOrder, batch);
            });
        }

        return null;
    }

    private void registerBatchPurchase(InboundOrder inboundOrder, Batch batch) {
        PurchaseRecord purchaseRecord = new PurchaseRecord();
        purchaseRecord.setOrderDate(inboundOrder.getOrderDate());
        purchaseRecord.setBatch(batch);
        purchaseRecord.setOrderNumber(inboundOrder.getOrderNumber());
        purchaseRecord.setQuantity(batch.getInitialQuantity());
        purchaseRecord.setPrice(batch.getPricePerUnit());
        createInboundOrderRegistrer(purchaseRecord);
    }


    private void verifyTemperatureInboundOrder(InboundOrder inboundOrder, Sector sector) {
        inboundOrder.getBatches().forEach(batch -> {
            if (batch.getMinimumTemperature() < batch.getCurrentTemperature()){
                // Lança erro de temperatura
            }

            if (batch.getMinimumTemperature() > sector.getTemperature()){
                // Lança erro de temperatura
            }
        });
    }


    private PurchaseRecord createInboundOrderRegistrer(PurchaseRecord purchaseRecord) {
        try {
            return inboundOrderRegisterRepository.save(purchaseRecord);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
