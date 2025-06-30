package com.ecommerce.product_service.model;

import lombok.Data;

@Data
public class ProductAvailabilityRequest {
    private Long productId;
    private int quantity;
}
