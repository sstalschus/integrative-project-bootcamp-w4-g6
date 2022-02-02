package com.mercadolibre.integrativeproject.repositories;

import com.mercadolibre.integrativeproject.entities.Product;
import com.mercadolibre.integrativeproject.enums.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Query("select p from Product p where p.category = ?1")
    List<Product> findByCategory(CategoryProduct queryType);
}
