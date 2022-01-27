package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Product;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Timestamp;

public class BatchDTO {

    @NotNull
    private Long batchNumber;

    @NotNull(message = "Batch need contain a product")
    private String productId;

    @Min(value = 1, message = "Initial quantity need above 0.")
    private Long initialQuantity;

    @Min(value = 0, message = "Quantity need above  or equals to 0.")
    private Long quantity;

    @NotNull(message = "Current temperature need to be informed")
    private Double currentTemperature;

    @NotNull(message = "Minimum temperature need to be informed")
    private Double minimumTemperature;

    @NotNull(message = "Mark need to be informed")
    private String mark;

    @NotNull
    @Future(message = "Batch need expiration date after current")
    private Timestamp expirationDate;

    @NotNull
    @Past(message = "Batch need fabrication date before current")
    private Timestamp fabricationDate;

    public BatchDTO() {
    }

    public Long getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(Long batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(Long initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getMinimumTemperature() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(Double minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Timestamp getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(Timestamp fabricationDate) {
        this.fabricationDate = fabricationDate;
    }
}