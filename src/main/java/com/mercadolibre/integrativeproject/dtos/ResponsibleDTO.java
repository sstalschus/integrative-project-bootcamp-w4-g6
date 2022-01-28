package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Responsible;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/** Validação de campos e criação de DTOs
 *
 * @author Jefferson Froes
 *
 * */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponsibleDTO {

    private Long id;

    @NotNull(message = "Name is not null.")
    @NotEmpty(message = "Name is not empty.")
    @Size(max = 50, message = "Name cannot exceed 50 characters.")
    private String name;

    @NotNull(message = "Employee Record is not null.")
    @NotNull(message = "Employee Record is not empty.")
    @Size(max = 5, message = "Employee Record cannot exceed 4 digites, them must be between 0-9 and 1 letter a-z.")
    @Pattern(regexp = "^\\d{4}[a-z\\d]$", message = "Employee Record must contain 4 digits between 0-9 and 1 letter a-z.")
    private String employeeRecord;

    /** Método utilizado para conversão do objetoDTO para objeto.
     *
     * @author Jefferson Froes
     *
     * @param responsibleDTO - Objeto a ser convertido.
     *
     * @return objeto convertido.
     *
     * */

    public static Responsible convert(ResponsibleDTO responsibleDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(responsibleDTO, Responsible.class);
    }

    /** Método utilizado para conversão do objeto para objetoDTO.
     *
     * @author Jefferson Froes
     *
     * @param responsible - Objeto a ser convertido.
     *
     * @return objeto convertido.
     *
     * */

    public static ResponsibleDTO convert(Responsible responsible){
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(responsible, ResponsibleDTO.class);
    }

}
