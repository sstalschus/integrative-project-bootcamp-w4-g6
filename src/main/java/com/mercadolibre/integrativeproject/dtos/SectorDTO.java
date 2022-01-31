package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.InventaryRegister;
import com.mercadolibre.integrativeproject.entities.Sector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

/** DTO de setor
 *
 * @author Lorraine Mendes
 * */

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
    private List<BatchDTO> lots;

    @NotNull(message = "O campo não pode estar vazio")
    private Double capacity;

    @NotNull(message = "O campo não pode estar vazio")
    private Double temperature;


    public Sector convert() {
        return Sector.builder()
                .id(this.id)
                .name(this.name)
                .sectorType(this.sectorType)
                .lots(lots.stream().map(BatchDTO::coverte).collect(Collectors.toList()))
                .capacity(this.capacity)
                .temperature(this.temperature)
                .build();
    }

    public static SectorDTO convert(Sector sector) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(sector, SectorDTO.class);
    }
}
