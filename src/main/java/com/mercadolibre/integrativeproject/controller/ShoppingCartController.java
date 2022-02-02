package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.CreateCustomerDTO;
import com.mercadolibre.integrativeproject.dtos.PucharseOrderDTO;
import com.mercadolibre.integrativeproject.dtos.TotalPriceDTO;
import com.mercadolibre.integrativeproject.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
