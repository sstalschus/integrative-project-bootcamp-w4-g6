package com.mercadolibre.integrativeproject.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Entity
public class Batch {

    @Id
    private Long id;
    //    @OneToOne
//    private Seller seller;
    @OneToOne
    private Product product;
    private Long initialQuantity;
    private Long quantity;
    private String mark;
    private Timestamp expirationDate;
    private Timestamp fabricationDate;

    public Batch() {
    }

    public Batch(Product product, Long quantity, String mark, Timestamp expirationDate, Timestamp fabricationDate) {
        this.product = product;
        this.quantity = quantity;
        this.mark = mark;
        this.expirationDate = expirationDate;
        this.fabricationDate = fabricationDate;
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