package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Adverts;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/** Entidade de DTO de Anúncios
 *
 * @author Daniel Ramos
 *
 * */
public class AdvertsDTO {

    private Long id;

    @NotNull(message = "Name is not null.")
    @NotEmpty(message = "Name is not empty.")
    @Size(max = 100, message = "Name cannot exceed 100 characters.")
    private String name;

    @NotNull
    private LocalDate advertsDate;

    public static Adverts convert(AdvertsDTO advertsDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(advertsDTO, Adverts.class);
    }
    public static AdvertsDTO convert(Adverts adverts) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(adverts, AdvertsDTO.class);
    }
}