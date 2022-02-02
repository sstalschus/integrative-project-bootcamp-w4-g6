package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** Entidade de DTO de Customer
 *
 * @author Samuel Stalschus
 *
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerDTO {

    private Long id;

    @NotNull(message = "Name is not null.")
    @NotEmpty(message = "Name is not empty.")
    @Size(max = 50, message = "Name cannot exceed 50 characters.")
    private String name;

    @Valid
    private AddressDTO address;

    @Valid
    private StorageDTO storage;

    public static Customer convert(CreateCustomerDTO customerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerDTO, Customer.class);
    }
    public static CreateCustomerDTO convert(Customer customer) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customer, CreateCustomerDTO.class);
    }
}
