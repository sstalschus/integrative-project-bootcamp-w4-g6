package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.ProductPerSector;
import com.mercadolibre.integrativeproject.entities.Storage;
import com.mercadolibre.integrativeproject.entities.StorageProductInfoPerSector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageProductInfoPerSectorDTO {

    private Long storageId;
    private String storageName;
    private List<ProductPerSectorDTO> productPerSectorList;

    public static StorageProductInfoPerSectorDTO convert(StorageProductInfoPerSector storageProductInfoPerSector) {
        List<ProductPerSectorDTO> productPerSectorDTOList = storageProductInfoPerSector.getProductPerSectorList().stream()
                .map(ProductPerSectorDTO::convert)
                .collect(Collectors.toList());

        return StorageProductInfoPerSectorDTO.builder()
                .storageId(storageProductInfoPerSector.getStorage().getId())
                .storageName(storageProductInfoPerSector.getStorage().getName())
                .productPerSectorList(productPerSectorDTOList)
                .build();
    }

}
