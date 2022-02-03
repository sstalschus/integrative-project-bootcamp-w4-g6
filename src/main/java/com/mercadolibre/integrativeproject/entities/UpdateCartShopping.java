package com.mercadolibre.integrativeproject.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartShopping {

    private Long cartId;
    private Long orderToDelete;
    private AdvertsInShoppingCart advertsInShoppingCart;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getOrderToDelete() {
        return orderToDelete;
    }

    public void setOrderToDelete(Long advertsInCart) {
        this.orderToDelete = advertsInCart;
    }

    public AdvertsInShoppingCart getAdvertsInShoppingCart() {
        return advertsInShoppingCart;
    }

    public void setAdvertsInShoppingCart(AdvertsInShoppingCart advertsInShoppingCart) {
        this.advertsInShoppingCart = advertsInShoppingCart;
    }
}
