package com.nikolakostic.isa_ecommerce.product.repository;

import com.nikolakostic.isa_ecommerce.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String name, String description);

}
