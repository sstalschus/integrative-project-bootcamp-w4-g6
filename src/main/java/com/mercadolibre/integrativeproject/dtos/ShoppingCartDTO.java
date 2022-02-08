package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.AdvertsInShoppingCart;
import com.mercadolibre.integrativeproject.entities.Customer;
import com.mercadolibre.integrativeproject.entities.ShoppingCart;
import com.mercadolibre.integrativeproject.enums.StatusShoppingCart;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.List;

/** Entidade de DTO do carrinho de compras
 *
 * @author Arthur Amorim
 *
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartDTO {
    private Long id;

    private StatusShoppingCart status = StatusShoppingCart.ACTIVE;

    private Customer customer;

    private List<AdvertsInShoppingCart> advertsInShoppingCart;


    public static ShoppingCartDTO convert(ShoppingCart shoppingCart) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(shoppingCart, ShoppingCartDTO.class);
    }

    public static ShoppingCart convert(ShoppingCartDTO shoppingCartDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(shoppingCartDTO, ShoppingCart.class);
    }
}
