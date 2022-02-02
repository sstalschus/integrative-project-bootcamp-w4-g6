package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class PucharseOrderDTO {

    @Valid
    private CreateShoppingCartDTO pucharseOrder;
}
