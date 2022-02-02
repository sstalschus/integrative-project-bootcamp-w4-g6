package com.mercadolibre.integrativeproject.entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/** Entidade Supplier
 *
 * @author Jefferson Froes
 *
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column(unique = true)
    private String cnpj;

    @NotNull
    @OneToOne
    private Address address;

}
