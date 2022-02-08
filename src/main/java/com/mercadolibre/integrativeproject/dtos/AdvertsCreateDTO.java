package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Adverts;
import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Responsible;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

/** Entidade de DTO de Anúncios
 *
 * @author Daniel Ramos
 *
 * */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertsCreateDTO {

    private Long id;

    @NotNull(message = "Name is not null.")
    @NotEmpty(message = "Name is not empty.")
    @Size(max = 100, message = "Name cannot exceed 100 characters.")
    private String name;

    @NotNull(message = "Deve ser informado o numero do lote do anuncio")
    private Long batchId;

    @NotNull(message = "Deve ser informado um valor para o anuncio")
    private BigDecimal price;

    @NotNull
    private LocalDate createdAt;

    @NotNull
    private Long responsibleId;

    public Adverts convert() {
        Responsible responsible = new Responsible();
        Batch batch = new Batch();
        batch.setId(this.batchId);
        responsible.setId(this.getResponsibleId());
        return Adverts.builder()
                .id(this.id)
                .name(this.name)
                .batch(batch)
                .price(this.price)
                .createdAt(this.createdAt)
                .responsible(responsible)
                .build();
    }
    public static AdvertsCreateDTO convert(Adverts adverts) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(adverts, AdvertsCreateDTO.class);
    }
}