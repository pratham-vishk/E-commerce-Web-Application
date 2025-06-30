package com.ecommerce.product_service.model;

import com.ecommerce.product_service.Entity.Product;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class ProductResponse {

    private Long productId;
    private String name;
    private String description;
    private Double price;
    private String category;
    private String brand;
    private List<String> imageUrls;
    private Instant createdAt;

    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .brand(product.getBrand())
                .imageUrls(product.getImageUrls())
                .createdAt(product.getCreatedAt())
                .build();
    }

}
