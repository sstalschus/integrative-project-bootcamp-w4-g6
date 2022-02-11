package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.PurchaseDTO;
import com.mercadolibre.integrativeproject.entities.Purchase;
import com.mercadolibre.integrativeproject.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/** Controller de registro do Purchase
 *
 * @author Daniel Ramos
 *
 * */
@RestController
@RequestMapping(value = "/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    /** Método usado para criar um Purchase.
     *
     * @author Daniel Ramos
     * @param purchaseDTO - recebe um purchaseDTO para converter em purchase
     * @return retorna um purchase convertido em purchaseDTO
     *
     * */
    @PostMapping(value = "/create")
    public ResponseEntity<PurchaseDTO> create(@Valid @RequestBody PurchaseDTO purchaseDTO){
        Purchase purchase = purchaseService.create(purchaseDTO.convert());
        return ResponseEntity.ok(PurchaseDTO.convert(purchase));
    }

    /** Método usado para obter um Purchase pelo ID.
     *
     * @author Daniel Ramos
     *
     * @return Purchase
     *
     * */
    @GetMapping(value = "/{id}")
    public ResponseEntity<PurchaseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(PurchaseDTO.convert(purchaseService.getById(id)));
    }

    /** Método usado para obter um Purchase pelo change-status.
     *
     * @author Daniel Ramos
     *
     * @return Purchase
     *
     * */
    @PostMapping(value = "/change-status")
    public ResponseEntity<PurchaseDTO> getById(@RequestParam Long purchaseId, @RequestParam String status){
        return ResponseEntity.ok(PurchaseDTO.convert(purchaseService.changeStatus(purchaseId, status)));
    }

}
