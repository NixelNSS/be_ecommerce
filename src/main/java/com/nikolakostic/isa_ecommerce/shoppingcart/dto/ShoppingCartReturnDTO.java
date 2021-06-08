package com.nikolakostic.isa_ecommerce.shoppingcart.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartReturnDTO {
    private final Long id;
    private final Double amount;
    private final Long count;
    private final List<ProductCountDTO> productCountList;
}
