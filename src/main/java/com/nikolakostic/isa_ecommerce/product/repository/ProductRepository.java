package com.nikolakostic.isa_ecommerce.product.repository;

import com.nikolakostic.isa_ecommerce.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceBetween(Double startPrice, Double endPrice);

    List<Product> findAllByNameContainsOrDescriptionContains(String name, String description);

    List<Product> findAllBySubcategory_Category_IdInAndPriceBetween(List<Long> categoryIds, Double startPrice, Double endPrice);

    List<Product> findAllBySubcategory_IdInAndPriceBetween(List<Long> subcategoryIds, Double startPrice, Double endPrice);

}
