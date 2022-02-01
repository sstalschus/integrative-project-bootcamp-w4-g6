package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.entities.Storage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
// id name address sectors
public class StorageDTO {

    private Long id;

    @NotNull(message = "O nome do armazém não pode estar vazio")
    @NotEmpty(message = "O nome do armazém não pode estar vazio")
    @Size(max = 100, message = "O nome do armazém não pode exceder 100 caracteres")
    private String name;

    @NotNull(message = "O endereço não pode estar vazio")
    @NotEmpty(message = "O endereço não pode estar vazio")
    @Size(max = 255, message = "O endereço não pode exceder 255 caracteres")
    private String address;

    @NotNull(message = "O setor não pode estar vazio")
    private List<SectorDTO> sectorsList = new ArrayList<>();

    public StorageDTO(){

    }

    public Long getId() {return id;
    }

    public void setId(Long id) {this.id = id;
    }

    public String getName() {return name;
    }

    public void setName(String name) {this.name = name;
    }

    public String getAddress() {return address;
    }

    public void setAddress(String address) {this.address = address;
    }

    public List<SectorDTO> getSectorsList() {return sectorsList;
    }

    public void setSectorsList(List<SectorDTO> sectorsList) {this.sectorsList = sectorsList;
    }

    public Storage convert() {
        return Storage.builder()
                .id(this.id)
                .name(this.name)
                .sectorsList(this.sectorsList.stream().map(SectorDTO::convert).collect(Collectors.toList())).build();
    }

}
