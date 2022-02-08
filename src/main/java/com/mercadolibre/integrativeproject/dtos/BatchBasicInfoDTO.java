package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Product;
import lombok.*;

/** Entidade de DTO da Informação básica do lote
 *
 * @author Arthur Amorim
 *
 * */
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
