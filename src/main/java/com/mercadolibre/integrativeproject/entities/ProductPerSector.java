package com.mercadolibre.integrativeproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductPerSector {

    private Sector sector;

    private List<Batch> batches = new ArrayList<>();
}
