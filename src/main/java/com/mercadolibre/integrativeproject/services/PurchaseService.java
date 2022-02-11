package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.Purchase;
import com.mercadolibre.integrativeproject.entities.ShoppingCart;
import com.mercadolibre.integrativeproject.enums.StatusPurchase;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.exceptions.RepositoryException;
import com.mercadolibre.integrativeproject.repositories.PurchaseRepository;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import com.mercadolibre.integrativeproject.services.interfaces.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service para a entidade de registro de Purchase.
 *
 * @author Daniel Ramos
 */
@Service
public class PurchaseService implements ICrudServiceInterface<Purchase, Long> {

    private PurchaseRepository purchaseRepository;

    private ShoppingCartService shoppingCartService;

    public PurchaseService(PurchaseRepository purchaseRepository, ShoppingCartService shoppingCartService) {
        this.purchaseRepository = purchaseRepository;
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * Método usado para criar um novo registro de purchase.
     *
     * @param purchase - registro de purchase.
     * @return Registro de purchase criado.
     * @author Daniel Ramos
     */
    @Override
   public Purchase create(Purchase purchase) {
       ShoppingCart shoppingCart = shoppingCartService.getById(purchase.getShoppingCart().getId());
       if (shoppingCart == null) {
           throw new NotFoundException("Status not found");
       }
       purchase.setStatus(StatusPurchase.OPEN);
       return purchaseRepository.save(purchase);
   }

    /**
     * Método usado para buscar um registro de responsible pelo id.
     *
     * @param purchaseId - id para busca.
     * @return Registro de purchase por id.
     * @author Daniel Ramos
     */
    @Override
    public Purchase getById(Long purchaseId) throws NotFoundException {
        return purchaseRepository.findById(purchaseId).orElseThrow(() -> new NotFoundException("Purchase not found."));
    }

    /**
     * Método usado para pegar todos os registros de purchase.
     *
     * @return Lista com os registros de purchase.
     * @author Daniel Ramos
     */
    @Override
    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }

    /**
     * Método usado para atualizar o registro purchase.
     *
     * @param purchase - objeto que recebe os dados. O id do objeto a ser atualizado
     *
     * @author Daniel Ramos
     *
     */
    @Override
    public Purchase update(Purchase purchase) {return purchaseRepository.save(purchase);
    }

    /**
     * Método usado para deletar o registro purchase.
     *
     * @param purchaseId - id do objeto a ser deletado
     * @throws RepositoryException - trata erro ao deletar purchase.
     * @author Daniel Ramos
     */
    @Override
    public void delete(Long purchaseId) throws RepositoryException{
        try{
            purchaseRepository.deleteById(purchaseId);

        } catch(Exception e) {
            throw new RepositoryException("Error by delete purchase");
        }

    }

    /**
     * Método usado para atualizar o status purchase.
     *
     * @param status - objeto que recebe os dados. O id do objeto a ser atualizado
     *
     * @author Daniel Ramos
     *
     */
    public Purchase changeStatus(Long id, String status){
        StatusPurchase statusPurchase = StatusPurchase.valueOf(status.toUpperCase());
        Purchase purchase = purchaseRepository.findById(id).orElse(null);
        purchase.setStatus(statusPurchase);
        update(purchase);
        return purchase;
    }


}
