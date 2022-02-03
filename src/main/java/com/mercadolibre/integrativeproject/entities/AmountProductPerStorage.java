package com.mercadolibre.integrativeproject.entities;

import com.mercadolibre.integrativeproject.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmountProductPerStorage {

    private Product product;

    private Storage storage;

    private Long quantity;
}
