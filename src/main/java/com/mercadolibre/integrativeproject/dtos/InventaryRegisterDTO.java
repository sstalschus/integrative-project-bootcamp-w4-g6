package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.InventaryRegister;
import com.mercadolibre.integrativeproject.enums.RegisterInventaryType;
import com.mercadolibre.integrativeproject.util.EnumValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventaryRegisterDTO {

    @EnumValidator(
            enumClazz = RegisterInventaryType.class,
            message = "Your transaction must be of type CREDIT or DEBIT."
    )
    private RegisterInventaryType type;

//    @valid
//    private Representant representant;
//
//    @valid
//    private Batch batch;

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
