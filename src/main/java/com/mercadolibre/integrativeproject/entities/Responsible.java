package com.mercadolibre.integrativeproject.entities;

import lombok.*;
import javax.persistence.*;

/** Entidade Responsible
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
public class Responsible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(unique = true)
    private String employeeRecord;

    @OneToOne
    private User user;
}
