package com.mercadolibre.integrativeproject.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchStockListDTO {
    private List<BatchStockDTO> batchStock;
}
