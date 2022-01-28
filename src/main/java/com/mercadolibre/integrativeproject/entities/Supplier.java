package com.mercadolibre.integrativeproject.entities;

import lombok.*;
import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column(unique = true)
    private String cnpj;

//    @Column
//    @OneToOne
//    private Address address;

}
