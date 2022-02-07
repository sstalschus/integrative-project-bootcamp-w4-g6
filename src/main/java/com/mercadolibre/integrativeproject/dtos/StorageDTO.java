package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Address;
import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.entities.Storage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
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
public class StorageDTO {

    private Long id;

    @NotNull(message = "O nome do armazém não pode estar vazio")
    @NotEmpty(message = "O nome do armazém não pode estar vazio")
    @Size(max = 100, message = "O nome do armazém não pode exceder 100 caracteres")
    private String name;

    @Valid
    private AddressDTO address;

    @NotNull(message = "O setor não pode estar vazio")
    private List<SectorDTO> sectorsList = new ArrayList<>();

    public StorageDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<SectorDTO> getSectorsList() {
        return sectorsList;
    }

    public void setSectorsList(List<SectorDTO> sectorsList) {
        this.sectorsList = sectorsList;
    }

    public Storage convert() {
        return Storage.builder()
                .id(this.id)
                .name(this.name)
                .address(AddressDTO.convert(this.address))
                .sectorsList(this.sectorsList.stream().map(SectorDTO::convert).collect(Collectors.toList())).build();
    }

    public StorageDTO convert(Storage storage) {
        this.address = AddressDTO.convert(storage.getAddress());
        this.name = storage.getName();
        this.sectorsList = storage.getSectorsList().stream().map(SectorDTO::convert).collect(Collectors.toList());
        this.id = storage.getId();
        return this;
    }
}
