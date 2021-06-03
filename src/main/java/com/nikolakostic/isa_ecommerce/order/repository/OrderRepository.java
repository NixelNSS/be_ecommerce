package com.nikolakostic.isa_ecommerce.order.repository;

import com.nikolakostic.isa_ecommerce.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
