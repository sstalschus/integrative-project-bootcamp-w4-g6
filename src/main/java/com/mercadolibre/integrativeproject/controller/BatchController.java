package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.BatchDTO;
import com.mercadolibre.integrativeproject.services.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BatchDTO batchDTO){
        return ResponseEntity.ok(batchService.create(batchDTO.coverte()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(batchService.getById(id));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(batchService.getAll());
    }

}
