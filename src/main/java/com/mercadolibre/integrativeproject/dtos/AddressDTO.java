package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/** Entidade de DTO do Endereço
 *
 * @author Daniel Ramos
 *
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;

    @NotNull(message = "O cep não pode estar vazio")
    @Pattern(regexp = "^[0-9]{5}[0-9]{3}$", message = "ZIP code is not valid")
    @Size(max = 8, message = "O cep não pode exceder 8 caracteres")
    private String zipCode;

    @NotNull(message = "O nome da rua não pode estar vazio")
    @Size(max = 100, message = "O nome da rua não pode exceder 100 caracteres")
    private String street;

    @NotNull(message = "O nome do bairro não pode estar vazio")
    @Size(max = 70, message = "O nome do bairro não pode exceder 70 caracteres")
    private String district;

    @NotNull(message = "O nome da cidade não pode estar vazio")
    @Pattern(regexp = "^[A-Z].*$", message = "O nome da cidade deve começar com letra maiuscula")
    @Size(max = 155, message = "O nome da cidade não pode exceder 155 caracteres")
    private String city;

    @NotNull(message = "O nome do estado não pode estar vazio")
    @Size(max = 155, message = "O nome do estado não pode exceder 155 caracteres")
    @Pattern(regexp = "^[A-Z].*$", message = "O nome do estado deve começar com letra maiuscula")
    private String state;


    public Long getId() { return id;
    }

    public void setId(Long id) {this.id = id;
    }

    public String getZipCode() {return zipCode;
    }

    public void setZipCode(String zipCode) {this.zipCode = zipCode;
    }

    public String getStreet() { return street;
    }

    public void setStreet(String street) {this.street = street;
    }

    public String getDistrict() {return district;
    }

    public void setDistrict(String district) {this.district = district;
    }

    public String getCity() {return city;
    }

    public void setCity(String city) {this.city = city;
    }

    public String getState() {return state;
    }

    public void setState(String state) {this.state = state;
    }

    public String getCountry() {return country;
    }

    public void setCountry(String country) {this.country = country;
    }

    @NotNull(message = "O nome do país não pode estar vazio")
    @Pattern(regexp = "^[A-Z].*$", message = "O nome do país deve começar com letra maiuscula")
    private String country;

    public static AddressDTO convert(Address address) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(address, AddressDTO.class);
    }

    public static Address convert(AddressDTO addressDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(addressDTO, Address.class);
    }

}
