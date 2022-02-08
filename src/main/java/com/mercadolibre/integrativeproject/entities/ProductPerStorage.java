package com.mercadolibre.integrativeproject.entities;

import lombok.*;

import java.util.List;

/** Entidade de ProductPerStorage
 *
 * @author Arthur Amorim
 *
 * */
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class ProductPerStorage {

    private Storage storage;
    private List<ProductPerSector> productPerSectorList;

}
