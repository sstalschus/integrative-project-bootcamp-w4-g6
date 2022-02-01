package com.mercadolibre.integrativeproject.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@Entity
public class Product {

    @Id
    private Long id;
    @NotNull
    private String name;
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
