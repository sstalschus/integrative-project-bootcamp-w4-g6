package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.InboundOrder;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.entities.Storage;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** Inbound Service
 * @author Arthur Amorim
 * @author Lorraine Mendes
 * */

@Service
public class InboundService {
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
            batchService.create(inboundOrder.getBatches());
        }

        return null;
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


}
