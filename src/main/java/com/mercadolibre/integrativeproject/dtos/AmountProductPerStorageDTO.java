package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.AmountProductPerStorage;
import com.mercadolibre.integrativeproject.entities.Storage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/** Entidade de DTO de Quantidade de produto por DTO de armazenamento
 *
 * @author Jefferson Froes
 *
 * */
public class AmountProductPerStorageDTO {

    private Long storageId;

    private String storageName;

    private Long productId;

    private Long quantity;

    public AmountProductPerStorageDTO() {
    }

    public AmountProductPerStorageDTO(Long storageId, Long productId, String storageName, Long quantity) {
        this.storageId = storageId;
        this.productId = productId;
        this.storageName = storageName;
        this.quantity = quantity;
    }

    public Long getStorageId() {
        return storageId;
    }

    public AmountProductPerStorageDTO setStorageId(Long storageId) {
        this.storageId = storageId;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public AmountProductPerStorageDTO setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public String getStorageName() {
        return storageName;
    }

    public AmountProductPerStorageDTO setStorageName(String storageName) {
        this.storageName = storageName;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public AmountProductPerStorageDTO setQuantity(Long quantity) {
        if (quantity == null){
            this.quantity = 0L;
        }else{
            this.quantity = quantity;
        }
        return this;
    }

    public static AmountProductPerStorageDTO convert(AmountProductPerStorage amountProductPerStorage){
        AmountProductPerStorageDTO amountProductPerStorageDTO = new AmountProductPerStorageDTO();
        return amountProductPerStorageDTO
                .setStorageId(amountProductPerStorage.getStorage().getId())
                .setStorageName(amountProductPerStorage.getStorage().getName())
                .setProductId(amountProductPerStorage.getProduct().getId())
                .setQuantity(amountProductPerStorage.getQuantity());
    }
}
