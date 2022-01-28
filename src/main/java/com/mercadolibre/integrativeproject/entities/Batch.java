package com.mercadolibre.integrativeproject.entities;


import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

//@Builder
@Entity
public class Batch {

    @Id
    private Long id;
    //    @OneToOne
//    private Seller seller;
    @NotNull
    @OneToOne
    private Product product;
    @Min(value = 1)
    private Long initialQuantity;
    @Min(value = 0)
    private Long quantity;
    @NotNull
    private Double currentTemperature;
    @NotNull
    private Double minimumTemperature;
    private String mark;
    @NotNull
    private Timestamp expirationDate;
    @NotNull
    private Timestamp fabricationDate;

    public Batch() {
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

    public Long getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(Long initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
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

    public boolean hasProductToSell(Long quantity) {
        return quantity >= this.quantity;
    }

    private void decrementProductQuantity(Long quantity) {
        this.quantity = this.quantity - quantity;
    }

    public void sellProductOnBatch(Long quantity){
        if (hasProductToSell(quantity)) {
            decrementProductQuantity(quantity);
        }

        //Lanca erro -> nao possui a quantidade solicitada disponivel no estoque
    }
}