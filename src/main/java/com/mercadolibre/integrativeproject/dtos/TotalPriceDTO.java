package com.mercadolibre.integrativeproject.dtos;

import lombok.*;

/** Entidade de DTO de valor total
 *
 * @author Samuel Stalschus
 *
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TotalPriceDTO {
    private double totalPrice;
}
