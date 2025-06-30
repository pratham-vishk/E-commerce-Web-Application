package com.ecommerce.product_service.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdatedEvent {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private String category;
    private String brand;
}
