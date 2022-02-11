package com.mercadolibre.integrativeproject.entities;

import com.mercadolibre.integrativeproject.enums.StatusPurchase;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/** Entidade de Purchase
 *
 * @author Daniel Ramos
 *
 * */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private ShoppingCart shoppingCart;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private StatusPurchase status = StatusPurchase.OPEN;
}
