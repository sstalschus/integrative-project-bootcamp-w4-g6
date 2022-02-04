package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.ProductDTO;
import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/create")
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO){
        Product product = productService.create(productDTO.convert());
        return ResponseEntity.ok(ProductDTO.convert(product));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(ProductDTO.convert(productService.getById(id)));
    }
}
