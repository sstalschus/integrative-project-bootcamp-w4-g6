package com.mercadolibre.integrativeproject.dtos;

import lombok.*;

import java.util.List;

/** Entidade de DTO de adição da Lista do estoque do lote
 *
 * @author Lorraine Mendes
 *
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchStockListDTO {
    private List<BatchStockDTO> batchStock;
}
