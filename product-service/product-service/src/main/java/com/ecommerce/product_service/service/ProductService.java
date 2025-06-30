package com.ecommerce.product_service.service;

import com.ecommerce.product_service.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    CreateProductResponse createProduct(CreateProductRequest request);

    void updateProduct(Long productId, UpdateProductRequest request);

    void deleteProduct(Long productId);

    ProductResponse getProductById(Long productId);

    ProductPageResponse getAllProducts(
            String category,
            String brand,
            Double minPrice,
            Double maxPrice,
            String search,
            String sort,
            int page,
            int size
    );

    List<String> getAllCategories();

    List<ProductAvailabilityResponse> checkAvailability(List<ProductAvailabilityRequest> request);

    List<ProductSummaryResponse> getProductsByIds(List<Long> productIds);

    void uploadProductImages(Long productId, List<MultipartFile> files);
}
