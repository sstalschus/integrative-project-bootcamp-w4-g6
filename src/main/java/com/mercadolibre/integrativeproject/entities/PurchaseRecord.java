package com.mercadolibre.integrativeproject.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderNumber;

    private LocalDate orderDate;

    @OneToOne
    private Batch batch;

    @ManyToOne
    private Responsible responsible;

    @Column
    private BigDecimal price;

    @Column
    private Long quantity;


}
