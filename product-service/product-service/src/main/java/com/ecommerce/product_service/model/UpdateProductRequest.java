package com.ecommerce.product_service.model;

import lombok.Data;

@Data
public class UpdateProductRequest {
    private String description;
    private Double price;
}
