package com.mercadolibre.integrativeproject.dtos;


import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.enums.StorageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/** Entidade de DTO do Estoque do lote
 *
 * @author Lorraine Mendes, Arthur Amorim
 *
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BatchStockDTO {
    private Long batchNumber;
    private Long productId;
    private StorageType productTypeId;
    private Timestamp dueDate;
    private Long quantity;

    public BatchStockDTO convert(Batch batch){
        this.setBatchNumber(batch.getId());
        this.setProductId(batch.getProduct().getId());
        this.setProductTypeId(batch.getProduct().getCategory());
        this.setDueDate(batch.getExpirationDate());
        this.setQuantity(batch.getQuantity());

        return this;

    }

}
