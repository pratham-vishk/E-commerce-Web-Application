package com.ecommerce.order_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSummaryResponse {
    private Long productId;
    private String name;
    private Double price;
}
