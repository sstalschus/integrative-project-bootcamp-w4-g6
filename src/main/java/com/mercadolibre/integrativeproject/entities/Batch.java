package com.mercadolibre.integrativeproject.entities;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

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

    public Batch setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
        return this;
    }

    public Double getMinimumTemperature() {
        return minimumTemperature;
    }

    public Batch setMinimumTemperature(Double minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
        return this;
    }

    public Long getInitialQuantity() {
        return initialQuantity;
    }

    public Batch setInitialQuantity(Long initialQuantity) {
        this.initialQuantity = initialQuantity;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Batch setId(Long id) {
        this.id = id;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public Batch setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Batch setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getMark() {
        return mark;
    }

    public Batch setMark(String mark) {
        this.mark = mark;
        return this;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public Batch setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public Timestamp getFabricationDate() {
        return fabricationDate;
    }

    public Batch setFabricationDate(Timestamp fabricationDate) {
        this.fabricationDate = fabricationDate;
        return this;
    }

    public boolean hasProductToSell(Long quantity) {
        return quantity >= this.quantity;
    }

    private Batch decrementProductQuantity(Long quantity) {
        this.quantity = this.quantity - quantity;
        return this;
    }

    public void sellProductOnBatch(Long quantity){
        if (hasProductToSell(quantity)) {
            decrementProductQuantity(quantity);
        }

        //Lanca erro -> nao possui a quantidade solicitada disponivel no estoque
    }

}