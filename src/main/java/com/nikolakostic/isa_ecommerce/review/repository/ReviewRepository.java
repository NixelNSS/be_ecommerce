package com.nikolakostic.isa_ecommerce.review.repository;

import com.nikolakostic.isa_ecommerce.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
