package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.*;
import com.mercadolibre.integrativeproject.exceptions.MissingParamsException;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.exceptions.TemperatureException;
import com.mercadolibre.integrativeproject.repositories.InboundOrderRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.SizeLimitExceededException;
import java.math.BigInteger;
import java.util.List;

/** Inbound Service
 *
 * @author Arthur Amorim
 * @author Lorraine Mendes
 *
 * */

@Service
public class InboundService {

    private InboundOrderRegisterRepository inboundOrderRegisterRepository;

    private SectorService sectorService;

    private BatchService batchService;

    private ProductService productService;

    private StorageService storageService;

    private ResponsibleService responsibleService;

    public InboundService(InboundOrderRegisterRepository inboundOrderRegisterRepository, SectorService sectorService, BatchService batchService, ProductService productService, StorageService storageService, ResponsibleService responsibleService) {
        this.inboundOrderRegisterRepository = inboundOrderRegisterRepository;
        this.sectorService = sectorService;
        this.batchService = batchService;
        this.productService = productService;
        this.storageService = storageService;
        this.responsibleService = responsibleService;
    }

    /** Método usado para criar um novo pedido de entrada
     *
     * @author Lorraine Mendes, Arthur Amorim, Samuel Stalschus
     *
     * @param  inboundOrder - pedido de entrada
     *
     * @return pedido de entrada
     *
     * */
    public InboundOrder create(InboundOrder inboundOrder) throws RepositoryException, NotFoundException, MissingParamsException {

        verifyData(inboundOrder);

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

    /**
     * Método usado para verifica a data do inboundOrder.
     *
     * @param inboundOrder - objeto que verifica a data inboundOrder
     * @author Lorraine Mendes, Arthur Amorim, Samuel Stalschus
     *
     */
    protected void verifyData(InboundOrder inboundOrder) {
        if(inboundOrder.getWarehouseCode() == null
                || inboundOrder.getSectionCode() == null
                || inboundOrder.getResponsible() == null
                || inboundOrder.getResponsible().getId() == null
        ) throw new MissingParamsException("Missing params");
    }

    /**
     * Método usado para registrar compra em lote.
     *
     * @param inboundOrder - objeto que registra compra em lote
     * @author Lorraine Mendes, Arthur Amorim, Samuel Stalschus
     *
     */
    protected void registerBatchPurchase(InboundOrder inboundOrder, Batch batch) {
        PurchaseRecord purchaseRecord = new PurchaseRecord();
        purchaseRecord.setOrderDate(inboundOrder.getOrderDate());
        purchaseRecord.setBatch(batch);
        purchaseRecord.setOrderNumber(inboundOrder.getOrderNumber());
        purchaseRecord.setQuantity(batch.getInitialQuantity());
        purchaseRecord.setPrice(batch.getPricePerUnit());
        purchaseRecord.setResponsible(inboundOrder.getResponsible());
        createInboundOrderRegistrer(purchaseRecord);
    }

    /**
     * Método usado para verificar a temperatura do pedido de entrada.
     *
     * @param inboundOrder - objeto que verifica temperatura do pedido de entrada
     * @author Lorraine Mendes, Arthur Amorim, Samuel Stalschus
     *
     */
    protected void verifyTemperatureInboundOrder(InboundOrder inboundOrder, Sector sector) throws TemperatureException {
        inboundOrder.getBatches().forEach(batch -> {
            if (batch.getMinimumTemperature() < batch.getCurrentTemperature()){
                throw new TemperatureException("Current temperature of batch is not valid");
            }

            if (batch.getMinimumTemperature() > sector.getTemperature()){
                throw new TemperatureException("Temperature of batch and sector not valid");
            }
        });
    }

    /**
     * Método usado para criar a registro do pedido de entrada.
     *
     * @author Lorraine Mendes, Arthur Amorim, Samuel Stalschus
     *
     * @param purchaseRecord - objeto que cria o registro do pedido de entrada
     *
     * @return Registro de pedido de entrada
     *
     */
    protected PurchaseRecord createInboundOrderRegistrer(PurchaseRecord purchaseRecord) {
            return inboundOrderRegisterRepository.save(purchaseRecord);
    }

}
