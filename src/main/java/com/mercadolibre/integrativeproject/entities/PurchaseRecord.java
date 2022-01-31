package com.mercadolibre.integrativeproject.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
/** Entidade Registro de compra
 * @author Lorraine Mendes
 * */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseRecord {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Batch batch;

    @ManyToOne
    private Responsible responsible;

    @Column
    private BigInteger price;

    @Column
    private Long quantity;


}
