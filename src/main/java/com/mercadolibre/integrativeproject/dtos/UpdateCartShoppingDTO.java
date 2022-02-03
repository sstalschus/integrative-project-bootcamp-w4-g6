package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.AdvertsInShoppingCart;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartShoppingDTO {

    private Long cartId;
    private Long advertsInCart;
    private AdvertsInShoppingCart advertsInShoppingCart;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getAdvertsInCart() {
        return advertsInCart;
    }

    public void setAdvertsInCart(Long advertsInCart) {
        this.advertsInCart = advertsInCart;
    }

    public AdvertsInShoppingCart getAdvertsInShoppingCart() {
        return advertsInShoppingCart;
    }

    public void setAdvertsInShoppingCart(AdvertsInShoppingCart advertsInShoppingCart) {
        this.advertsInShoppingCart = advertsInShoppingCart;
    }
}
