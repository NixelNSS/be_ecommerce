package com.nikolakostic.isa_ecommerce.product.repository;

import com.nikolakostic.isa_ecommerce.product.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
