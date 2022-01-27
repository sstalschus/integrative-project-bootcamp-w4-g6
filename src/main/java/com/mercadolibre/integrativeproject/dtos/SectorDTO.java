package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Batch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectorDTO {

    private Long id;

    @NotNull(message = "O campo não pode estar vazio")
    @NotEmpty(message = "O campo não pode estar vazio")
    @Size(max = 30, message = "O comprimento do comodo não pode exceder 30 caracteres.")
    private String name;

    @NotNull(message = "O campo não pode estar vazio")
    @NotEmpty(message = "O campo não pode estar vazio")
    @Size(max = 20, message = "O comprimento do comodo não pode exceder 20 caracteres.")
    private String sectorType;

    @NotNull(message = "O campo não pode estar vazio")
    @NotEmpty(message = "O campo não pode estar vazio")
    private List<Batch> lots;

    @NotNull(message = "O campo não pode estar vazio")
    private Double capacity;

    @NotNull(message = "O campo não pode estar vazio")
    private Double temperature;

}
