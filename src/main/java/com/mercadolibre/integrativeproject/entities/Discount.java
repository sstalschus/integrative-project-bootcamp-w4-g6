package com.mercadolibre.integrativeproject.entities;

import com.mercadolibre.integrativeproject.enums.DiscountRange;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/** Entidade de desconto, para o requisito 6 de Samuel Stalschus
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
public class Discount implements Comparable<Discount> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Adverts advert;

    @Column
    private BigDecimal previousPrice;

    @Column
    private BigDecimal currentPrice;

    @Enumerated(EnumType.ORDINAL)
    private DiscountRange discountRange;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Override
    public int compareTo(Discount o) {
        if(o.discountRange.ordinal() < this.discountRange.ordinal())
            return 1;
        else if(o.discountRange.ordinal() > this.discountRange.ordinal())
            return -1;
        else
            return 1;
    }
}
