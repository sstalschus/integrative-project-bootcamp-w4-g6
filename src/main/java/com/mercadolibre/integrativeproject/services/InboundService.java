package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.*;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.repositories.InboundOrderRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.SizeLimitExceededException;
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
    private ProductService productService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ResponsibleService responsibleService;

    public InboundOrder create(InboundOrder inboundOrder){
        Storage storage = storageService.getValidStorage(inboundOrder.getWarehouseCode());

        Sector sector = sectorService.getValidSectorOnStorage(inboundOrder.getSectionCode(), storage);

        Responsible responsible = responsibleService.getById(inboundOrder.getResponsible().getId());

        if (responsible.getId() == null || !sector.getResponsible().equals(responsible)) {
            throw new RepositoryException("Representante nao pertence ao setor informado");
        }

        inboundOrder.setResponsible(responsible);

        verifyTemperatureInboundOrder(inboundOrder, sector);

        inboundOrder.getBatches().forEach(batch -> {
            batch.setProduct(productService.getById(batch.getProduct().getId()));
            batch.setQuantity(batch.getInitialQuantity());
        });

        if (sectorService.hasSectorCapacity(inboundOrder.getBatches(), sector)){
            inboundOrder.getBatches().forEach(batch -> {
                Batch batchSaved = batchService.create(batch);
                sector.getLots().add(batchSaved);
                registerBatchPurchase(inboundOrder, batch);
            });

            return inboundOrder;
        } else {
            throw new NotFoundException("Setor nao possui capacidade para comportar este lotes");
        }
    }

    private void registerBatchPurchase(InboundOrder inboundOrder, Batch batch) {
        PurchaseRecord purchaseRecord = new PurchaseRecord();
        purchaseRecord.setOrderDate(inboundOrder.getOrderDate());
        purchaseRecord.setBatch(batch);
        purchaseRecord.setOrderNumber(inboundOrder.getOrderNumber());
        purchaseRecord.setQuantity(batch.getInitialQuantity());
        purchaseRecord.setPrice(batch.getPricePerUnit());
        purchaseRecord.setResponsible(inboundOrder.getResponsible());
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
