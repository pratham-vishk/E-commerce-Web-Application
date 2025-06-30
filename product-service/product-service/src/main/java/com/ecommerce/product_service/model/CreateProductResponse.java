package com.ecommerce.product_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateProductResponse {
    private Long productId;
    private String message;
}
