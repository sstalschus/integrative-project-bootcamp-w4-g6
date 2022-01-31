package com.mercadolibre.integrativeproject.entities;

import com.mercadolibre.integrativeproject.enums.RegisterInventaryType;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private RegisterInventaryType type;

    @NotNull
    @ManyToOne
    private Responsible responsible;

    @NotNull
    @OneToOne
    private Batch batch;

    @NotNull
    private Integer quantity;
}
