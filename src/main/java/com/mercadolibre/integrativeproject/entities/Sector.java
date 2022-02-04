package com.mercadolibre.integrativeproject.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mercadolibre.integrativeproject.enums.StorageType;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/** Entidade setor
 *
 * @author Lorraine Mendes
 * */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToOne
    private Storage storage;

    @OneToOne
    @JsonBackReference
    private Responsible responsible;

    @Column
    private String sectorType;

    @Column
    @Enumerated(EnumType.STRING)
    private StorageType categoryProduct;

    @OneToMany
    @Column
    @JsonBackReference
    private List<Batch> lots = new ArrayList<>();

    @Column
    private Double capacity;

    @Column
    private Double temperature;

    public void addBatch(Batch batch) {
        this.lots.add(batch);
    }
}
