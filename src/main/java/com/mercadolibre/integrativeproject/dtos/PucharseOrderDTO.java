package com.mercadolibre.integrativeproject.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mercadolibre.integrativeproject.entities.Customer;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;

/** Entidade de DTO ordem de compra (usada para seguir o padrão do requisito 2)
 *
 * @author Samuel Stalschus
 *
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PucharseOrderDTO {

    @Valid
    private CreateShoppingCartDTO purcharseOrder;
}
