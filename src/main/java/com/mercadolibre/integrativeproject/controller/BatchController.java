package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.BatchDTO;
import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.services.BatchService;
import com.mercadolibre.integrativeproject.services.interfaces.ICrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Controller de Lote
 *
 * @author Arthur Amorim
 *
 * */
@RestController
@RequestMapping(value = "/batch")
public class BatchController {

    @Autowired
    private ICrudServiceInterface batchService;

    /** Método usado para obter um lote pelo ID.
     *
     * @author Arthur Amorim
     *
     * @return Lote
     *
     * */
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(batchService.getById(id));
    }

    /** Método usado para ober uma lista do lote pelo ID.
     *
     * @author Arthur Amorim
     *
     * @return Lista dos Lotes acoplado ao Response Entity
     *
     * */
    @GetMapping(value = "/list")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(batchService.getAll());
    }

}
