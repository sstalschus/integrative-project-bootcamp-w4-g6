package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.ProductDTO;
import com.mercadolibre.integrativeproject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.create(productDTO.convert()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }
}
