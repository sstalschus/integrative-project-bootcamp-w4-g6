package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.*;
import com.mercadolibre.integrativeproject.entities.AdvertsInShoppingCart;
import com.mercadolibre.integrativeproject.entities.ShoppingCart;
import com.mercadolibre.integrativeproject.entities.UpdateCartShopping;
import com.mercadolibre.integrativeproject.enums.CategoryProduct;
import com.mercadolibre.integrativeproject.services.ProductService;
import com.mercadolibre.integrativeproject.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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

    /** Método usado para criar um carrinho, e anuncios no carrinho relacionados a um determinado cliente.
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

    /** Método usado para retornar os produtos de um determinado pedido
     *
     * @author Samuel Stalschus
     *
     * @return Produtos de um pedido
     *
     * */
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<List<AdvertsInShoppingCartDTO>> create(@Valid @PathVariable Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                shoppingCartService.getProductsByShoppingCart(orderId)
                        .stream()
                        .map(AdvertsInShoppingCartDTO::convert)
                        .collect(Collectors.toList()));
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


    @PutMapping(value = "/update-cart")
    public ResponseEntity<?> updateOrdersOnCart(@RequestParam Long cartId, @Nullable Long deleteOrder, @Nullable @RequestBody AdvertsInShoppingCart advertsInShoppingCart) {
        ShoppingCartDTO shoppingCartDTO = ShoppingCartDTO.convert(shoppingCartService.updateOrdersCart(new UpdateCartShopping(cartId, deleteOrder, advertsInShoppingCart)));
        return ResponseEntity.status(200).body(shoppingCartDTO);
    }
}
