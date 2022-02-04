package com.mercadolibre.integrativeproject.dtos;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@Builder
public class BatchDTO {

    private Long id;

    @NotNull
    private Long batchNumber;

    @NotNull(message = "Batch need contain a product")
    private Long productId;

    @Min(value = 1, message = "Initial quantity need above 0.")
    private Long initialQuantity;

    @Min(value = 0, message = "Quantity need above  or equals to 0.")
    private Long quantity;

    @NotNull(message = "Current temperature need to be informed")
    private Double currentTemperature;

    @NotNull(message = "Minimum temperature need to be informed")
    private Double minimumTemperature;

    @NotNull(message = "Mark need to be informed")
    private String brand;

    @NotNull
    @Future(message = "Batch need expiration date after current")
    private Timestamp expirationDate;

    @NotNull
    @Past(message = "Batch need fabrication date before current")
    private Timestamp fabricationDate;

    @NotNull
    private BigDecimal pricePerUnit;

    public BatchDTO() {
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(Long batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public Batch coverte() {
        Batch batch = new Batch();
        return batch
                .setId(this.id)
                .setProduct(Product.builder().id(this.productId).build())
                .setInitialQuantity(this.initialQuantity)
                .setQuantity(this.quantity)
                .setCurrentTemperature(this.currentTemperature)
                .setMinimumTemperature(this.minimumTemperature)
                .setBrand(this.brand)
                .setBatchNumber(this.batchNumber)
                .setExpirationDate(this.expirationDate)
                .setFabricationDate(this.fabricationDate)
                .setPricePerUnit(this.pricePerUnit);
    }

    public static BatchDTO convert(Batch batch) {
        return BatchDTO.builder()
                .batchNumber(batch.getBatchNumber())
                .id(batch.getId())
                .productId(batch.getProduct().getId())
                .initialQuantity(batch.getInitialQuantity())
                .quantity(batch.getQuantity())
                .minimumTemperature(batch.getMinimumTemperature())
                .currentTemperature(batch.getCurrentTemperature())
                .brand(batch.getBrand())
                .expirationDate(batch.getExpirationDate())
                .fabricationDate(batch.getFabricationDate())
                .pricePerUnit(batch.getPricePerUnit())
                .build();

    }
}