package com.mercadolibre.integrativeproject.services.strategies;

import com.mercadolibre.integrativeproject.entities.Batch;
import com.mercadolibre.integrativeproject.entities.ProductPerSector;
import com.mercadolibre.integrativeproject.entities.ProductPerStorage;

import java.util.Comparator;
import java.util.List;

public enum SortProductPerStorage {

    /**
     * Realiza a ordenacao por Id de lote
     *
     * @author Arthur Amorim
     *
     * @param productPerStorageList - List de product Per Storage.
     *
     * @return List<ProductPerStorage> - Retorna a Lista ordenada de acordo com o tipo de ordenação necessaria
     * */
    L {
        @Override
        public List<ProductPerStorage> sort(List<ProductPerStorage> productPerStorageList) {

            productPerStorageList.forEach(productPerStorage -> {
                productPerStorage.getProductPerSectorList().forEach(productPerSector -> {
                    productPerSector.getSector().getLots().sort(Comparator.comparing(Batch::getId));
                });

                productPerStorage.getProductPerSectorList().sort(Comparator.comparing(productPerSector -> productPerSector.getBatches().stream()
                        .mapToLong(Batch::getId).min().getAsLong()));
            });

            productPerStorageList.sort(Comparator.comparing(productPerStorage ->
                productPerStorage.getProductPerSectorList().stream().mapToLong(productPerSector ->
                     productPerSector.getBatches().stream().mapToLong(Batch::getId).min().getAsLong()
                ).min().getAsLong()
            ));

            return productPerStorageList;
        }
    },
    /**
     * Realiza a ordenação pelo campo Name do Produto de forma descendente
     *
     * @author Samuel Stalschus
     *
     * @param products - List de produtos não ordenada.
     *
     * @return List<Product> - Retorna a Lista ordenada de forma descendente
     * */
    C {
        @Override
        public List<ProductPerStorage> sort(List<ProductPerStorage> productPerStorageList) {

            productPerStorageList.forEach(productPerStorage -> {
                productPerStorage.getProductPerSectorList().forEach(productPerSector -> {
                    productPerSector.getSector().getLots().sort(Comparator.comparing(Batch::getQuantity));
                });

                productPerStorage.getProductPerSectorList().sort(Comparator.comparing(productPerSector -> productPerSector.getBatches().stream()
                        .mapToLong(Batch::getQuantity).sum()));
            });

            productPerStorageList.sort(Comparator.comparing(productPerStorage ->
                    productPerStorage.getProductPerSectorList().stream().mapToLong(productPerSector ->
                            productPerSector.getBatches().stream().mapToLong(Batch::getQuantity).sum()
                    ).sum()
            ));

            return productPerStorageList;
        }
    },
    /**
     * Realiza a ordenacao pelo campo Price do produto de forma decrescente
     *
     * @author Arthur Amorim
     *
     * @param products - List de produtos não ordenada.
     *
     * @return List<Product> - Retorna a Lista ordenada de forma decrescente
     * */
    F {
        public List<ProductPerStorage> sort(List<ProductPerStorage> productPerStorageList) {

            productPerStorageList.forEach(productPerStorage -> {
                productPerStorage.getProductPerSectorList().forEach(productPerSector -> {
                    productPerSector.getSector().getLots().sort(Comparator.comparing(batch -> batch.getExpirationDate().getTime()));
                });

                productPerStorage.getProductPerSectorList().sort(Comparator.comparing(productPerSector -> productPerSector.getBatches().stream()
                        .mapToLong(batch -> batch.getExpirationDate().getTime()).max().getAsLong()));
            });

            productPerStorageList.sort(Comparator.comparing(productPerStorage ->
                    productPerStorage.getProductPerSectorList().stream().mapToLong(productPerSector ->
                            productPerSector.getBatches().stream().mapToLong(batch -> batch.getExpirationDate().getTime()).max().getAsLong()
                    ).max().getAsLong()
            ));

            return productPerStorageList;
        }
    };
    /**
     * Metodo base de ordenação
     * */
    public List<ProductPerStorage> sort(List<ProductPerStorage> productPerSectors) {
        return productPerSectors;
    }
}
