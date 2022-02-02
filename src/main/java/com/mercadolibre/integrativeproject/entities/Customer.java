package com.mercadolibre.integrativeproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/** Entidade de cliente
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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToOne
    private Address address;

    @OneToOne
    private Storage storage;

    @OneToMany
    private List<ShoppingCart> shoppingCarts;
}
