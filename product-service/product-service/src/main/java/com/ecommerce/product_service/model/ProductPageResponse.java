package com.ecommerce.product_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductPageResponse {
    private List<ProductListItem> content;
    private int totalPages;
    private long totalItems;

    @Data
    @AllArgsConstructor
    public static class ProductListItem {
        private Long productId;
        private String name;
        private Double price;
        private String category;
    }

    public static ProductPageResponse dummy() {
        return new ProductPageResponse(
                List.of(new ProductListItem(1L, "iPhone 15 Pro", 1299.00, "Smartphones")),
                1,
                1
        );
    }
}
