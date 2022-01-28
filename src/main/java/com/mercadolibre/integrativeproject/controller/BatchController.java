package com.mercadolibre.integrativeproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {

    @PostMapping
    public ResponseEntity<?> create(){
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
