package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.dtos.PucharseOrderDTO;
import com.mercadolibre.integrativeproject.entities.AdvertsInShoppingCart;
import com.mercadolibre.integrativeproject.entities.Customer;
import com.mercadolibre.integrativeproject.entities.ShoppingCart;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.ShoppingCartRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/** Service de carrinho de compras
 *
 * @author Samuel Stalschus
 *
 * */
@Service
public class ShoppingCartService implements IShoppingCartService<ShoppingCart, Long, PucharseOrderDTO> {

    ShoppingCartRepository shoppingCartRepository;

    CustomerService customerService;

    AdvertsInShoppingCartService advertsInShoppingCartService;

    AdvertsService advertsService;

    /**
     *
     * Foi criado o construtor para promover o princípio da injeção de dependências
     *
     * */
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, CustomerService customerService, AdvertsInShoppingCartService advertsInShoppingCartService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.customerService = customerService;
        this.advertsInShoppingCartService = advertsInShoppingCartService;
    }

    /** Método usado para criar um carrinho de compras e atribuir a um cliente
     *
     * @author Samuel Stalschus
     *
     * @param  pucharseOrderDTO - DTO de pucharse order
     *
     * @return Valor total do carrinho no tipo double
     *
     * @throws NotFoundException
     *
     * */
    @Override
    public double create(PucharseOrderDTO pucharseOrderDTO) {
        Customer customer = customerService.getById(pucharseOrderDTO.getPucharseOrder().getBuyerId());

        ShoppingCart shoppingCar = ShoppingCart.builder().customer(customer).build();

        List<AdvertsInShoppingCart> advertsInShoppingCarts = pucharseOrderDTO.getPucharseOrder().getProducts().stream().map(advert ->
                AdvertsInShoppingCart.builder()
                        .advert(advertsService.getById(advert.getAdvertId()))
                        .quantity(advert.getQuantity())
                        .shoppingCart(shoppingCar).build()).collect(Collectors.toList()
        );

        List<AdvertsInShoppingCart> advertsInShoppingCartsCreated = advertsInShoppingCartService.createMany(advertsInShoppingCarts);

        shoppingCar.setAdvertsInShoppingCart(advertsInShoppingCartsCreated);

        return advertsInShoppingCarts.stream().mapToDouble(advertInShopCart -> advertInShopCart.getAdvert().getPrice().doubleValue()).sum();
    }

    /** Método usado para pegar um carrinho de compras pelo ID
     *
     * @author Samuel Stalschus
     *
     * @param  id - Id do carrinho de compras
     *
     * @return Carrinho de compras
     *
     * @throws NotFoundException
     *
     * */
    @Override
    public ShoppingCart findById(Long id) throws NotFoundException {
        return shoppingCartRepository.findById(id).orElseThrow(() -> new NotFoundException("Shopping Cart not found"));
    }

    /** Método usado para obter todos os carrinhos de compras.
     *
     * @author Samuel Stalschus
     *
     * @return Lista com todos os carrinhos de compras
     *
     * */
    @Override
    public List<ShoppingCart> getAll() {
        return shoppingCartRepository.findAll();
    }
}