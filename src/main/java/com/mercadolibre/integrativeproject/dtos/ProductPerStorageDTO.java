package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.ProductPerSector;
import com.mercadolibre.integrativeproject.entities.ProductPerStorage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/** Entidade de DTO do Produto por armazém
 *
 * @author Arthur Amorim
 *
 * */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPerStorageDTO {

    private Long storageId;
    private String storageName;
    private List<ProductPerSectorDTO> productPerSectors;


    public static ProductPerStorageDTO convert(ProductPerStorage productPerStorage) {
        List<ProductPerSectorDTO> productPerSectorDTOList = productPerStorage.getProductPerSectorList().stream().map(ProductPerSectorDTO::convert).collect(Collectors.toList());
        return ProductPerStorageDTO.builder()
                .storageId(productPerStorage.getStorage().getId())
                .storageName(productPerStorage.getStorage().getName())
                .productPerSectors(productPerSectorDTOList)
                .build();
    }
}
