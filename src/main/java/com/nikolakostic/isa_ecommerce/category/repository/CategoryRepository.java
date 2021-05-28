package com.nikolakostic.isa_ecommerce.category.repository;

import com.nikolakostic.isa_ecommerce.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
