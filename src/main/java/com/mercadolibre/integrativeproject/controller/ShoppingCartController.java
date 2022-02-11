package com.mercadolibre.integrativeproject.controller;

import com.mercadolibre.integrativeproject.dtos.*;
import com.mercadolibre.integrativeproject.entities.AdvertsInShoppingCart;
import com.mercadolibre.integrativeproject.entities.UpdateCartShopping;
import com.mercadolibre.integrativeproject.enums.ProductType;
import com.mercadolibre.integrativeproject.enums.StorageType;
import com.mercadolibre.integrativeproject.services.BatchService;
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

    @Autowired
    BatchService batchService;

    /** Método usado para criar um carrinho, e anuncios no carrinho relacionados a um determinado cliente.
     *
     * @author Samuel Stalschus
     *
     * @return Valor total do carrinho
     *
     * */
    @PostMapping("/orders")
    public ResponseEntity<TotalPriceDTO> create(@Valid @RequestBody PucharseOrderDTO pucharseOrderDTO) {
        double value = shoppingCartService.create(pucharseOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(TotalPriceDTO.builder().totalPrice(value).build());
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

    /** Controller de produto por categoria.
     *
     * @author Lorraine Mendes
     *
     * @return Lista de produtos por categoria (Fresco, Congelado ou Refrigerado).
     * */
    @GetMapping(value = "/list")
    public ResponseEntity<List<ProductDTO>> getProductByCategory(@RequestParam StorageType queryType) {
        return ResponseEntity.ok(productService.getByCategory(queryType).stream().map(ProductDTO::convert).collect(Collectors.toList()));
    }


    /** Controller de produto por tipo. TASK 6
     *
     * @author Lorraine Mendes
     *
     * @return Lista com filtro de produtos por tipo (Vegetariano ou vegano)
     * */
    @GetMapping(value = "/type")
    public ResponseEntity<List<ProductDTO>> getProductByProductType(@RequestParam ProductType queryType) {
        return ResponseEntity.ok(productService.getByProductType(queryType).stream().map(ProductDTO::convert).collect(Collectors.toList()));
    }


    @PutMapping(value = "/update-cart")
    public ResponseEntity<?> updateOrdersOnCart(@RequestParam Long cartId, @Nullable Long deleteOrder, @Nullable @RequestBody AdvertsInShoppingCart advertsInShoppingCart) {
        ShoppingCartDTO shoppingCartDTO = ShoppingCartDTO.convert(shoppingCartService.updateOrdersCart(new UpdateCartShopping(cartId, deleteOrder, advertsInShoppingCart)));
        return ResponseEntity.status(204).body(shoppingCartDTO);
    }

    /** Controller de lotes por data de venciment.
     *
     * @author Lorraine Mendes e Arthur Amorim
     *
     * @return Lotess armazenados filtrado por data de vencimento
     * */
    @GetMapping(value = "/due-date")
    public ResponseEntity<BatchStockListDTO> getBatchesByDueDate(@RequestParam Integer numberOfDays, @RequestParam Long sector){
        List<BatchStockDTO> Batchs = batchService.getBatchesByDueDate(numberOfDays, sector)
                .stream()
                .map(batch -> new BatchStockDTO().convert(batch))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new BatchStockListDTO(Batchs));
    }


    /** Método usado para obter uma lista dos lotes pedidos que vão expirar em uma determinada quantidade de dias de uma determinada categoria de produtos
     *
     * @author Samuel Stalschus
     *
     * @param numberOfDays - numero de dias dentro do range de expiração de produto
     * @param category - categoria do produto
     * @param asc - ordenar lista de lotes em ordem ascedente
     *
     * @return Lista de lotes com base na data de validade e categoria
     *
     * */
    @GetMapping("/due-date/list")
    public ResponseEntity<BatchStockListDTO> getBatchsByDateExpiresAndCategoryProduct(@RequestParam Integer numberOfDays, @RequestParam StorageType category, @RequestParam boolean asc) {
        List<BatchStockDTO> batchs = batchService.getBatchesByProductCategoyAndDueDate(numberOfDays, category, asc)
                .stream()
                .map(batch -> new BatchStockDTO().convert(batch))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new BatchStockListDTO(batchs));
    }
}
