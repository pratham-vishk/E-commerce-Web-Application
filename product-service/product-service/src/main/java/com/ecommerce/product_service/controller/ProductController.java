package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.model.*;
import com.ecommerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 1. Create Product (Admin only)
    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    // 2. Update Product by ID (Admin only)
    @PutMapping("/{productId}")
    public ResponseEntity<Map<String, String>> updateProduct(
            @PathVariable Long productId,
            @RequestBody UpdateProductRequest request) {
        productService.updateProduct(productId, request);
        return ResponseEntity.ok(Map.of("message", "Product updated successfully"));
    }

    // 3. Delete Product by ID (Admin only)
    @DeleteMapping("/{productId}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok(Map.of("message", "Product deleted successfully"));
    }

    // 4. Get Product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    // 5. Get All Products with filtering
    @GetMapping
    public ResponseEntity<ProductPageResponse> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.getAllProducts(category, brand, minPrice, maxPrice, search, sort, page, size));
    }

    // 6. Get All Categories
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        return ResponseEntity.ok(productService.getAllCategories());
    }

    // 7. Check Product Availability
    @PostMapping("/check-availability")
    public ResponseEntity<List<ProductAvailabilityResponse>> checkAvailability(@RequestBody List<ProductAvailabilityRequest> request) {
        return ResponseEntity.ok(productService.checkAvailability(request));
    }

    // 8. Bulk Get Products by IDs
    @PostMapping("/bulk")
    public ResponseEntity<List<ProductSummaryResponse>> getBulkProducts(@RequestBody List<Long> productIds) {
        return ResponseEntity.ok(productService.getProductsByIds(productIds));
    }

    // 9. Upload Product Images
    @PostMapping("/{productId}/images")
    public ResponseEntity<Map<String, String>> uploadProductImages(
            @PathVariable Long productId,
            @RequestParam("files") List<MultipartFile> files) {
        productService.uploadProductImages(productId, files);
        return ResponseEntity.ok(Map.of("message", "Images uploaded successfully"));
    }
}
