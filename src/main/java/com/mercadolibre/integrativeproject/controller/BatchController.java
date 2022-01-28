package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.BatchDTO;
import com.mercadolibre.integrativeproject.dtos.InboundOrderDTO;
import com.mercadolibre.integrativeproject.dtos.SectionInboundOrderDTO;
import com.mercadolibre.integrativeproject.entities.InboundOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BatchDTO batchDTO){
        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity<?> getById(){
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(null);
    }

}
