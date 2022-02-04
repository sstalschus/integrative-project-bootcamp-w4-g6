package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.InventaryRegister;
import com.mercadolibre.integrativeproject.entities.Responsible;
import com.mercadolibre.integrativeproject.entities.Sector;
import com.mercadolibre.integrativeproject.entities.Storage;
import com.mercadolibre.integrativeproject.enums.StorageType;
import com.mercadolibre.integrativeproject.util.EnumValidator;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
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
@Builder
public class SectorDTO {

    private Long id;

    @NotNull(message = "O campo não pode estar vazio")
    @NotEmpty(message = "O campo não pode estar vazio")
    @Size(max = 30, message = "O comprimento do comodo não pode exceder 30 caracteres.")
    private String name;

    @NotNull(message = "O campo não pode estar vazio")
    private Long storageID;

    private Long responsibleId;

    private List<BatchDTO> lots = new ArrayList<>();

    @NotNull(message = "O campo não pode estar vazio")
    private Double capacity;

    @NotNull(message = "O campo não pode estar vazio")
    private Double temperature;

    @EnumValidator(
            enumClazz = StorageType.class,
            message = "The category must be type: FRESH, CHILLED or FROZEN."
    )
    private String category;


    public Sector convert() {
        Storage storage = new Storage();
        storage.setId(this.storageID);
        Responsible responsible =  new Responsible();
        responsible.setId(responsibleId);
        return Sector.builder()
                .id(this.id)
                .name(this.name)
                .lots(lots.stream().map(BatchDTO::coverte).collect(Collectors.toList()))
                .capacity(this.capacity)
                .responsible(responsible)
                .storage(storage)
                .sectorType(StorageType.valueOf(category))
                .temperature(this.temperature)
                .build();
    }

    public static SectorDTO convert(Sector sector) {
            return SectorDTO.builder()
                .id(sector.getId())
                .name(sector.getName())
                .lots(sector.getLots().stream().map(batch -> new BatchDTO().convert(batch)).collect(Collectors.toList()))
                .capacity(sector.getCapacity())
                .responsibleId(sector.getResponsible().getId())
                .storageID(sector.getStorage().getId())
                .category(sector.getSectorType().name())
                .temperature(sector.getTemperature())
                .build();
    }
}
