package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.enums.ProductType;
import com.mercadolibre.integrativeproject.enums.StorageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Repository do Produto
 *
 * @author Arthur Amorim, Lorraine Mendes
 *
 * */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Query("select p from Product p where p.category = ?1")
    List<Product> findByCategory(StorageType queryType);

    @Modifying
    @Query("select p from Product p where p.productType = ?1")
    List<Product> findByProductType(ProductType queryType);
}
