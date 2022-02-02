package com.mercadolibre.integrativeproject.entities;

import com.mercadolibre.integrativeproject.enums.TypeRegisterInventary;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/** Entidade de registro de estoque
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
public class InventaryRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private TypeRegisterInventary type;

    @NotNull
    @ManyToOne
    private Responsible responsible;

    @NotNull
    @OneToOne
    private Batch batch;

    @NotNull
    private Integer quantity;
}
