package com.mercadolibre.integrativeproject.entities;


import lombok.*;

import javax.persistence.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sector {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String sectorType;

    @OneToMany
    @Column
    private List<Batch> lots;

    @Column
    private Double capacity;

    @Column
    private Double temperature;

    public void addBatch(Batch batch) {
        this.lots.add(batch);
    }
}
