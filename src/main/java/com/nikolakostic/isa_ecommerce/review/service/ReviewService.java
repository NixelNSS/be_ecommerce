package com.nikolakostic.isa_ecommerce.review.service;

import com.nikolakostic.isa_ecommerce.order.entity.Order;
import com.nikolakostic.isa_ecommerce.order.exception.InvalidOrderException;
import com.nikolakostic.isa_ecommerce.order.service.OrderService;
import com.nikolakostic.isa_ecommerce.product.entity.Product;
import com.nikolakostic.isa_ecommerce.review.dto.RequestReviewDTO;
import com.nikolakostic.isa_ecommerce.review.dto.ResponseReviewDTO;
import com.nikolakostic.isa_ecommerce.review.entity.Review;
import com.nikolakostic.isa_ecommerce.review.repository.ReviewRepository;
import com.nikolakostic.isa_ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    public ResponseReviewDTO create(RequestReviewDTO dto) throws InvalidOrderException {
        Order order = this.orderService.getById(dto.getOrderId());
        Optional<Product> optionalProduct = order.getProducts().stream()
                .filter(product -> product.getId().equals(dto.getProductId()))
                .findFirst();
        if (!optionalProduct.isPresent()) throw new RuntimeException("Wrong order and product combination!");
        Review review = this.reviewRepository.save(new Review(
                dto.getValue(),
                this.userService.getAuthenticatedUser(),
                optionalProduct.get(),
                order
        ));
        return new ResponseReviewDTO(
                review.getId(),
                review.getValue()
        );
    }

}
