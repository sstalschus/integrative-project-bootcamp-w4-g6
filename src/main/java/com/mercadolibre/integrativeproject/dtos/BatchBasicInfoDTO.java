package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Product;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchBasicInfoDTO {

    private Long batchNumber;

    private Long productId;

    private Long quantity;

    public static BatchBasicInfoDTO convert(Batch batch) {
        return BatchBasicInfoDTO.builder()
                .batchNumber(batch.getId())
                .productId(batch.getProduct().getId())
                .quantity(batch.getQuantity())
                .build();
    }
}
