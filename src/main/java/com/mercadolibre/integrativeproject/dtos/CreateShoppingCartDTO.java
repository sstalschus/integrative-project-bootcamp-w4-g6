package com.mercadolibre.integrativeproject.dtos;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/** Entidade de DTO de criação de carrinho de compras
 *
 * @author Samuel Stalschus
 *
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateShoppingCartDTO {

    @NotNull(message= "buyerId not null")
    @Range(min = 1)
    private Long buyerId;

    @Valid
    private List<AddProductInShoppingCar> products;
}
