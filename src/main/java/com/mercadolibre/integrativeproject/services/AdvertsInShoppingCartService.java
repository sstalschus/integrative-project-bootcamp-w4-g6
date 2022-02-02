package com.mercadolibre.integrativeproject.services;

import com.mercadolibre.integrativeproject.entities.AdvertsInShoppingCart;
import com.mercadolibre.integrativeproject.exceptions.NotFoundException;
import com.mercadolibre.integrativeproject.repositories.AdvertsInShoppingCartRepository;
import com.mercadolibre.integrativeproject.services.interfaces.IAdvertsInShoppingCart;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/** Service de Anuncios no carrinho de compras
 *
 * @author Samuel Stalschus
 *
 * */
@Service
public class AdvertsInShoppingCartService implements IAdvertsInShoppingCart<AdvertsInShoppingCart, Long> {

    AdvertsInShoppingCartRepository advertsInShoppingCartRepository;

    ShoppingCartService shoppingCartService;

    /**
     *
     * Foi criado o construtor para promover o princípio da injeção de dependências
     *
     * */
    public AdvertsInShoppingCartService(AdvertsInShoppingCartRepository advertsInShoppingCartRepository, @Lazy ShoppingCartService shoppingCartService) {
        this.advertsInShoppingCartRepository = advertsInShoppingCartRepository;
        this.shoppingCartService = shoppingCartService;
    }

    /** Método usado para criar um novo anuncio no carrinho
     *
     * @author Samuel Stalschus
     *
     * @param  advertsInShoppingCart - Anuncio no carrinho
     *
     * @return Anuncio no carrinho criado
     *
     * */
    @Override
    public AdvertsInShoppingCart create(AdvertsInShoppingCart advertsInShoppingCart) {
        return advertsInShoppingCartRepository.save(advertsInShoppingCart);
    }

    /** Método usado para criar uma lista de Anuncios no carrinho
     *
     * @author Samuel Stalschus
     *
     * @param  advertsInShoppingCart - Anuncio no carrinho
     *
     * @return Lista de anuncios no carrinho criados
     *
     * */
    @Override
    public List<AdvertsInShoppingCart> createMany(List<AdvertsInShoppingCart> advertsInShoppingCart) {
        return advertsInShoppingCartRepository.saveAllAndFlush(advertsInShoppingCart);
    }

    /** Método usado para obter um Anuncio no carrinho pelo ID.
     *
     * @author Samuel Stalschus
     *
     * @param  id - Id do anuncio no carrinho.
     *
     * @return Anuncio no carrinho
     *
     * @throws NotFoundException
     *
     * */
    @Override
    public AdvertsInShoppingCart findById(Long id) throws NotFoundException {
        return advertsInShoppingCartRepository.findById(id).orElseThrow(() -> new NotFoundException("AdvertsInShoppingCart not found"));
    }

    /** Método usado para obter todos os anuncios no carrinho de compras de um determinado carrinho de compras
     *
     * @author Samuel Stalschus
     *
     * @return Lista com todos os anuncios no carrinho de compras
     *
     * @throws NotFoundException
     *
     * */
    @Override
    public List<AdvertsInShoppingCart> getAllByShoppingCartId(Long shoppingCartId) {
        shoppingCartService.findById(shoppingCartId);
        return advertsInShoppingCartRepository.findByShoppingCarId(shoppingCartId);
    }
}
