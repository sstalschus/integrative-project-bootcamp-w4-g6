package com.mercadolibre.integrativeproject.entities;

import com.mercadolibre.integrativeproject.enums.StatusShoppingCart;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/** Entidade de carrinho de compras
 *
 * @author Samuel Stalschus
 *
 * */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private StatusShoppingCart status = StatusShoppingCart.ACTIVE;

    @ManyToOne
    private Customer customer;

    @OneToMany
    private List<AdvertsInShoppingCart> advertsInShoppingCart;
}
