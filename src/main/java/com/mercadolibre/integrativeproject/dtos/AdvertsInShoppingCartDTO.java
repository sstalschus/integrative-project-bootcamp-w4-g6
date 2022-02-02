package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Adverts;
import com.mercadolibre.integrativeproject.entities.AdvertsInShoppingCart;
import com.mercadolibre.integrativeproject.entities.Customer;
import com.mercadolibre.integrativeproject.entities.ShoppingCart;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.sql.Timestamp;

/** Entidade de DTO de anuncios no carrinho
 *
 * @author Samuel Stalschus
 *
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertsInShoppingCartDTO {
    private Long id;

    private AdvertsDTO advert;

    private Integer quantity;

    private Timestamp putDate;

    public static AdvertsInShoppingCartDTO convert(AdvertsInShoppingCart advertsInShoppingCart) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(advertsInShoppingCart, AdvertsInShoppingCartDTO.class);
    }
}
