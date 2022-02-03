package com.mercadolibre.integrativeproject.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageProductInfoPerSector {

    private Storage storage;
    private List<ProductPerSector> productPerSectorList;
}
