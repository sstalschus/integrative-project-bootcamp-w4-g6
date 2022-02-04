package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.ProductPerSector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPerSectorDTO {

    private Long sectorId;
    private String sectorName;
    private List<BatchBasicInfoDTO> batchBasicInfoList;

    public static ProductPerSectorDTO convert(ProductPerSector productPerSector) {
        List<BatchBasicInfoDTO> basicInfo = productPerSector.getBatches().stream().map(BatchBasicInfoDTO::convert).collect(Collectors.toList());
        return ProductPerSectorDTO.builder()
                .sectorId(productPerSector.getSector().getId())
                .sectorName(productPerSector.getSector().getName())
                .batchBasicInfoList(productPerSector.getBatches().stream().map(BatchBasicInfoDTO::convert).collect(Collectors.toList()))
                .build();
    }
}
