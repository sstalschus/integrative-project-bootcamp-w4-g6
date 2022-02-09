package com.mercadolibre.integrativeproject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/** Entidade de anuncios no carrinho
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
public class AdvertsInShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonBackReference
    private ShoppingCart shoppingCart;

    @OneToOne
    private Adverts advert;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp putDate;
}
