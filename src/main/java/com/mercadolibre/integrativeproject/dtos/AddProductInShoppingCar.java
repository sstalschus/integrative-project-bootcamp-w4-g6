package com.mercadolibre.integrativeproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class AddProductInShoppingCar {

    @NotNull(message= "productId not null")
    @Range(min = 1)
    private Long advertId;

    private Integer quantity;
}
