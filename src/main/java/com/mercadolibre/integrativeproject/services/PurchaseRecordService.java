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

/** Service para a entidade Registro de compra.
 *
 * @author Lorraine Mendes Samuel Stalschus
 *
 * */
@Service
public class PurchaseRecordService implements IPurchaseRecordService<PurchaseRecord, Long> {

    private PurchaseRecordRepository purchaseRecordRepository;

    public PurchaseRecordService(PurchaseRecordRepository purchaseRecordRepository) {
        this.purchaseRecordRepository = purchaseRecordRepository;
    }

    /** Método usado para criar um novo Registro de compra
     *
     * @author Lorraine Mendes Samuel Stalschus
     *
     * @param  purchaseRecord - Registro de compra
     *
     * @return Registro de compra
     *
     * */
    @Override
    public PurchaseRecord create(PurchaseRecord purchaseRecord){
        return purchaseRecordRepository.save(purchaseRecord);
    }

    /** Método usado para obter um Registro de compra.
     *
     * @author Lorraine Mendes, Samuel Stalschus
     *
     * @param  purchaseRecordId - Id do purchaseRecord.
     *
     * @return Registro de compra que tenha o purchaseRecordId informado
     *
     * @throws NotFoundException
     *
     * */
    @Override
    public PurchaseRecord getById(Long purchaseRecordId) throws NotFoundException {
        return purchaseRecordRepository.findById(purchaseRecordId).orElseThrow(()->new NotFoundException("Purchase record not found"));
    }

    /** Método usado para pegar todos o Registro de compra
     *
     * @author Lorraine Mendes
     *
     * @return Lista com o Registro de compra
     *
     * */
    @Override
    public List<PurchaseRecord> getAll() {
            return purchaseRecordRepository.findAll();
    }

    /**
     * Método usado para atualizar o registro purchaseRecord.
     *
     * @param purchaseRecord - objeto que recebe os dados. O id do objeto a ser atualizado
     *
     * @author Lorraine Mendes, Samuel Stalschus
     *
     */
    @Override
    public PurchaseRecord update(PurchaseRecord purchaseRecord) {
        return purchaseRecordRepository.setPucharseRecordInfoById(purchaseRecord.getQuantity(), purchaseRecord.getPrice(),purchaseRecord.getId());
    }

    /**
     * Método usado para deletar o registro purchaseRecord.
     *
     * @author Lorraine Mendes, Samuel Stalschus.
     *
     * @param purchaseRecordId - id do objeto a ser deletado
     *
     * @throws RepositoryException - trata erro ao deletar purchaseRecord.
     */
    @Override
    public void delete(Long purchaseRecordId) throws RepositoryException {
        try {
            purchaseRecordRepository.deleteById(purchaseRecordId);

        } catch (Exception e) {
            throw new RepositoryException("Error in delete purcharse.");
        }
    }
}
