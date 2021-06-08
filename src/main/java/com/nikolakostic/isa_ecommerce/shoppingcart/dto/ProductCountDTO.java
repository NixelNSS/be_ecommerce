package com.nikolakostic.isa_ecommerce.shoppingcart.dto;

import com.nikolakostic.isa_ecommerce.product.entity.Product;
import lombok.Data;

@Data
public class ProductCountDTO {
    private final Product product;
    private final Long count;
}
