package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.dtos.PucharseOrderDTO;
import com.mercadolibre.integrativeproject.entities.AdvertsInShoppingCart;
import com.mercadolibre.integrativeproject.entities.Customer;
import com.mercadolibre.integrativeproject.entities.ShoppingCart;
import com.mercadolibre.integrativeproject.entities.UpdateCartShopping;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.ShoppingCartRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** Service de carrinho de compras
 *
 * @author Samuel Stalschus
 *
 * */
@Service
public class ShoppingCartService implements IShoppingCartService<ShoppingCart, Long, PucharseOrderDTO, AdvertsInShoppingCart> {

    ShoppingCartRepository shoppingCartRepository;

    CustomerService customerService;

    AdvertsInShoppingCartService advertsInShoppingCartService;

    AdvertsService advertsService;

    /**
     *
     * Foi criado o construtor para promover o princípio da injeção de dependências
     *
     * */
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, CustomerService customerService, AdvertsInShoppingCartService advertsInShoppingCartService, AdvertsService advertsService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.customerService = customerService;
        this.advertsInShoppingCartService = advertsInShoppingCartService;
        this.advertsService = advertsService;
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
        Customer customer = customerService.getById(pucharseOrderDTO.getPurcharseOrder().getBuyerId());

        ShoppingCart shoppingCar = generateShoppingCar(customer);

        List<AdvertsInShoppingCart> advertsInShoppingCartsCreated = generateAdvertsInShoppingCartsAndSave(pucharseOrderDTO, shoppingCar);

        shoppingCar.setAdvertsInShoppingCart(advertsInShoppingCartsCreated);

        shoppingCartRepository.save(shoppingCar);

        decrementProductByList(advertsInShoppingCartsCreated);

        return advertsInShoppingCartsCreated.stream().mapToDouble(advertInShopCart -> advertInShopCart.getAdvert().getPrice().doubleValue()).sum();
    }

    /** Método usado para gerar um novo Carrinho para o cliente
     *
     * @author Samuel Stalschus
     *
     * @param  customer - Cliente
     *
     * @return Carrinho de compras criado
     *
     * */
    public ShoppingCart generateShoppingCar(Customer customer) {
        return ShoppingCart.builder().customer(customer).build();
    }

    /** Método usado para gerar um novo Carrinho para o cliente
     *
     * @author Samuel Stalschus
     *
     * @param  pucharseOrderDTO - Ordem de compra
     * @param  shoppingCar - Carrinho
     *
     * @return Lista com anuncios no carrinhos criados
     *
     * */
    public List<AdvertsInShoppingCart> generateAdvertsInShoppingCartsAndSave(PucharseOrderDTO pucharseOrderDTO, ShoppingCart shoppingCar) {
        List<AdvertsInShoppingCart> advertsInShoppingCarts = pucharseOrderDTO.getPurcharseOrder().getProducts().stream().map(advert ->
                AdvertsInShoppingCart.builder()
                        .advert(advertsService.getById(advert.getAdvertId()))
                        .quantity(advert.getQuantity())
                        .shoppingCart(shoppingCar).build()).collect(Collectors.toList()
        );
        return advertsInShoppingCartService.createMany(advertsInShoppingCarts);
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
    public ShoppingCart getById(Long id) throws NotFoundException {
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

    /** Método usado para obter todos os produtos de um carrinho
     *
     * @author Samuel Stalschus
     *
     * @return Lista com todos os carrinhos de compras
     *
     * */
    @Override
    public List<AdvertsInShoppingCart> getProductsByShoppingCart(Long shoppingCartId) {
       return this.getById(shoppingCartId).getAdvertsInShoppingCart();
    }

    public ShoppingCart updateOrdersCart(UpdateCartShopping updateCartShopping) {
        ShoppingCart shoppingCart = shoppingCartRepository.getById(updateCartShopping.getCartId());
        if (updateCartShopping.getOrderToDelete() != null) {
            removeOrderOnCart(shoppingCart, updateCartShopping.getOrderToDelete());
        }

        if (updateCartShopping.getAdvertsInShoppingCart() != null) {
            addOrderOnCart(updateCartShopping.getAdvertsInShoppingCart(), shoppingCart);
        }

        return shoppingCart;
    }

    protected List<AdvertsInShoppingCart> removeOrderOnCart(ShoppingCart shoppingCart, Long orderId) {
        return shoppingCart.getAdvertsInShoppingCart().stream()
                .filter(advertsInShoppingCart -> !Objects.equals(advertsInShoppingCart.getId(), orderId))
                .collect(Collectors.toList());
    }

    protected ShoppingCart addOrderOnCart(AdvertsInShoppingCart advertsInShoppingCart, ShoppingCart shoppingCart){
        shoppingCart.getAdvertsInShoppingCart().add(advertsInShoppingCart);
        return shoppingCart;
    }

    /** Método usado para fazer o decrement de uma lista de produtos que esteja sendo colocada no carrinho
     *
     * @author Samuel Stalschus
     *
     * */
    @Override
    public void decrementProductByList(List<AdvertsInShoppingCart> advertsInShoppingCart) {
        advertsInShoppingCart.forEach(
                advert -> advert.getAdvert().getBatch().sellProductOnBatch(Long.valueOf((advert.getQuantity()))));
    }
}
