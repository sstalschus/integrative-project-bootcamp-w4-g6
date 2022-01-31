package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.InventaryRegister;
import com.mercadolibre.integrativeproject.enums.RegisterInventaryType;
import com.mercadolibre.integrativeproject.util.EnumValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import javax.validation.constraints.*;

/** Entidade de DTO de Registro de Estoque.
 *
 * @author Samuel Stalschus
 *
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventaryRegisterDTO {

    @EnumValidator(
            enumClazz = RegisterInventaryType.class,
            message = "The transaction type must be CREDIT or DEBIT."
    )
    private String type;

    @Valid
    private ResponsibleDTO responsible;

    @Valid
    private BatchDTO batch;

    @Min(value = 0, message = "The value must be greater then one.")
    private Integer quantity;

    public static InventaryRegister convert(InventaryRegisterDTO inventaryRegisterDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(inventaryRegisterDTO, InventaryRegister.class);
    }
    public static InventaryRegisterDTO convert(InventaryRegister inventaryRegister) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(inventaryRegister, InventaryRegisterDTO.class);
    }
}
