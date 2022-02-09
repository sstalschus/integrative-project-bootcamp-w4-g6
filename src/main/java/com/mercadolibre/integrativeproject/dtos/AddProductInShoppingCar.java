package com.mercadolibre.integrativeproject.dtos;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/** Entidade de DTO de adição de produtos no carrinho de compras
 *
 * @author Samuel Stalschus
 *
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddProductInShoppingCar {

    @NotNull(message= "productId not null")
    private Long advertId;

    private Integer quantity;
}
