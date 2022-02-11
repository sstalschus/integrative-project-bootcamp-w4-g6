package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Adverts;
import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Responsible;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Entidade de DTO de Anúncios
 *
 * @author Daniel Ramos
 *
 * */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertsDTO {

    private Long id;

    @NotNull(message = "Name is not null.")
    @NotEmpty(message = "Name is not empty.")
    @Size(max = 100, message = "Name cannot exceed 100 characters.")
    private String name;


    @NotNull(message = "Deve ser informado um valor para o anuncio")
    private BigDecimal price;

    @NotNull
    private LocalDate createdAt;

    private BatchDTO batch;

    public static AdvertsDTO convert(Adverts adverts) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(adverts, AdvertsDTO.class);
    }

    public static List<AdvertsDTO> convert(List<Adverts> adverts) {
        ArrayList<AdvertsDTO> advertsDTOS = new ArrayList<>();
        adverts.forEach(advert -> advertsDTOS.add(convert(advert)));
        return advertsDTOS;
    }
}