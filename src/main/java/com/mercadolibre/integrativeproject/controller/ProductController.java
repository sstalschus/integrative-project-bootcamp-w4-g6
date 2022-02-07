package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.ProductDTO;
import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/** Controller de registro do Produto
 *
 * @author Arthur Amorim
 *
 * */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /** Método usado para criar um Product.
     *
     * @author Arthur Amorim
     * @param productDTO - recebe um productDTO para converter em product
     * @return retorna um product convertido em productDTO
     *
     * */
    @PostMapping(value = "/create")
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO){
        Product product = productService.create(productDTO.convert());
        return ResponseEntity.ok(ProductDTO.convert(product));
    }

    /** Método usado para obter um produto pelo ID.
     *
     * @author Arthur Amorim
     *
     * @return Produto
     *
     * */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(ProductDTO.convert(productService.getById(id)));
    }
}
