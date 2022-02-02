package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.CreateCustomerDTO;
import com.mercadolibre.integrativeproject.dtos.ProductDTO;
import com.mercadolibre.integrativeproject.dtos.PucharseOrderDTO;
import com.mercadolibre.integrativeproject.dtos.TotalPriceDTO;
import com.mercadolibre.integrativeproject.enums.CategoryProduct;
import com.mercadolibre.integrativeproject.services.ProductService;
import com.mercadolibre.integrativeproject.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/** Controller de carrinho de compras com endpoints do requisito 2
 *
 * @author Samuel Stalschus
 *
 * */
@RestController
@RequestMapping("/fresh-products")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ProductService productService;

    /** Método usado para um carrinho, e anuncios no carrinho relacionados a um determinado cliente.
     *
     * @author Samuel Stalschus
     *
     * @return Valor total do carrinho
     *
     * */
    @PostMapping("/orders")
    public ResponseEntity<TotalPriceDTO> create(@Valid @RequestBody PucharseOrderDTO pucharseOrderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(TotalPriceDTO.builder().totalPrice(shoppingCartService.create(pucharseOrderDTO)).build());
    }

    /** Controller de registro de supplier.
     *
     * @author Jefferson Froes, Samuel Stalschus
     *
     * @return Lista de todos os produtos.
     * */
    @GetMapping(value = "")
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(productService.getAll().stream().map(ProductDTO::convert).collect(Collectors.toList()));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<ProductDTO>> getProductByCategory(@RequestParam CategoryProduct queryType) {
        return ResponseEntity.ok(productService.getByCategory(queryType).stream().map(ProductDTO::convert).collect(Collectors.toList()));
    }
}
