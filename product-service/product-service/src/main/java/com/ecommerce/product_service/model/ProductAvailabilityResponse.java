package com.ecommerce.product_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductAvailabilityResponse {
    private Long productId;
    private boolean available;
    private String reason;
    private Double price;
}

