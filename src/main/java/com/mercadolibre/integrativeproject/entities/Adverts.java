package com.mercadolibre.integrativeproject.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
// Zipcode street district city state country
public class Adverts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    private BigDecimal price;

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

    public LocalDate getAdvertsDate() {
        return advertsDate;
    }

    public void setAdvertsDate(LocalDate advertsDate) {
        this.advertsDate = advertsDate;
    }

    private LocalDate advertsDate;

}