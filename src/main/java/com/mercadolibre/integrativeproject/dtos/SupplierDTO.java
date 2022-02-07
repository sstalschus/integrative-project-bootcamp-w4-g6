package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Validação de campos e criação de DTOs
 *
 * @author Jefferson Froes
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {

    private Long id;

    @NotNull(message = "Name is not null.")
    @NotEmpty(message = "Name is not empty.")
    @Size(max = 100, message = "Name cannot exceed 100 characters.")
    private String name;

    @NotNull(message = "Supplier`s CNPJ is not null.")
    @NotEmpty(message = "Supplier`s CNPJ is not empty.")
    @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})"
            , message = "Supplier`s CNPJ invalid")
    private String cnpj;

    @Valid
    private AddressDTO address;

    /**
     * Método utilizado para conversão do objetoDTO para objeto.
     *
     * @author Jefferson Froes
     * @param supplierDTO - Objeto a ser convertido.
     * @return objeto convertido.
     *
     */
    public static Supplier convert(SupplierDTO supplierDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(supplierDTO, Supplier.class);
    }

    /**
     * Método utilizado para conversão do objeto para objetoDTO.
     *
     * @author Jefferson Froes
     * @param supplier - Objeto a ser convertido.
     * @return objeto convertido.
     *
     */
    public static SupplierDTO convert(Supplier supplier) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(supplier, SupplierDTO.class);
    }
}
