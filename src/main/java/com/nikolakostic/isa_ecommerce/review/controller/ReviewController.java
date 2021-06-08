package com.nikolakostic.isa_ecommerce.review.controller;

import com.nikolakostic.isa_ecommerce.order.exception.InvalidOrderException;
import com.nikolakostic.isa_ecommerce.review.dto.RequestReviewDTO;
import com.nikolakostic.isa_ecommerce.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("")
    public ResponseEntity<?> addReview(@RequestBody RequestReviewDTO dto) {
        try {
            return ResponseEntity.ok().body(this.reviewService.create(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
