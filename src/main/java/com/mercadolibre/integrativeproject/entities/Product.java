package com.mercadolibre.integrativeproject.entities;

import com.mercadolibre.integrativeproject.enums.ProductType;
import com.mercadolibre.integrativeproject.enums.StorageType;
import lombok.AllArgsConstructor;
import lombok.Builder;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/** Entidade de Product
 *
 * @author Arthur Amorim, Lorraine Mendes
 *
 * */
@Builder
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private StorageType category;

    /** Atributo para tipo de produto. .
     *
     * @author Lorraine Mendes TASK 6
     * */
    @Enumerated(EnumType.ORDINAL)
    private ProductType productType;

    /** Metodo get para tipo de produto. .
     *
     * @author Lorraine Mendes TASK 6
     * */
    public ProductType getProductType() {
        return productType;
    }

    /** Metodo set para tipo de produto .
     *
     * @author Lorraine Mendes TASK 6
     * */
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Double getVolumn() {
        return volumn;
    }

    public void setVolumn(Double volumn) {
        this.volumn = volumn;
    }

    private Double volumn;


    public Product() {
    }

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StorageType getCategory() {
        return category;
    }

    public void setCategory(StorageType category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
