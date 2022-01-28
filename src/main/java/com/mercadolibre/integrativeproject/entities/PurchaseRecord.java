package com.mercadolibre.integrativeproject.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

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

//    @OneToMany
//    @Column
//    private Representative representative;

    @Column
    private BigInteger price;

    @Column
    private Long quantity;


}
