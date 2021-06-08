package com.nikolakostic.isa_ecommerce.review.dto;

import lombok.Data;

@Data
public class RequestReviewDTO {
    public final Integer value;
    public final Long productId;
    public final Long orderId;
}
