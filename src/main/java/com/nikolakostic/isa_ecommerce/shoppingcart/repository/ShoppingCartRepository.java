package com.nikolakostic.isa_ecommerce.shoppingcart.repository;

import com.nikolakostic.isa_ecommerce.shoppingcart.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
