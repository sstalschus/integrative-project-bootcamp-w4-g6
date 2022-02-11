package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.enums.DiscountRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/** Entidade de DTO de valor de desconto calculado com o range, requisito 6 Samuel Stalschus
 *
 * @author Arthur Amorim
 *
 * */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ValueOfDiscountDTO {
    private BigDecimal value;
    private DiscountRange range;
}
