package com.ecommerce.product_service.service.imple;

import com.ecommerce.product_service.Entity.Product;
import com.ecommerce.product_service.Exception.ResourceNotFoundException;
import com.ecommerce.product_service.model.*;
import com.ecommerce.product_service.repository.ProductRespository;
import com.ecommerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImple implements ProductService {

    private final ProductRespository productRepository;
    // private final EventPublisher eventPublisher; // optional

    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .brand(request.getBrand())
                .category(request.getCategory())
                .imageUrls(request.getImageUrls())
                .build();

        productRepository.save(product);

        // Optionally emit event
        // eventPublisher.publishProductCreated(product);

        return new CreateProductResponse(product.getId(), "Product created successfully");
    }

    @Override
    public void updateProduct(Long id, UpdateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        productRepository.save(product);

    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = getProductOrThrow(productId);
        productRepository.delete(product);

        // Optionally emit event
        // eventPublisher.publishProductDeleted(productId);
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        Product product = getProductOrThrow(productId);
        return ProductResponse.from(product);
    }

    @Override
    public ProductPageResponse getAllProducts(String category, String brand, Double minPrice, Double maxPrice,
                                              String search, String sort, int page, int size) {
        // This will use specification or query DSL in real implementation.
        // Here you can return dummy or filtered data.
        return ProductPageResponse.dummy(); // placeholder
    }

    @Override
    public List<String> getAllCategories() {
        return productRepository.findDistinctCategories();
    }

    @Override
    public List<ProductAvailabilityResponse> checkAvailability(List<ProductAvailabilityRequest> request) {
        return request.stream()
                .map(req -> {
                    Product product = productRepository.findById(req.getProductId())
                            .orElse(null);
                    if (product == null) {
                        return new ProductAvailabilityResponse(req.getProductId(), false, "Not found", null);
                    }
                    // Assume product has stock info (not shown here)
                    boolean available = true; // placeholder
                    return new ProductAvailabilityResponse(product.getId(), available, null, product.getPrice());
                }).collect(Collectors.toList());
    }

    @Override
    public List<ProductSummaryResponse> getProductsByIds(List<Long> productIds) {
        List<Product> products = productRepository.findAllById(productIds);
        return products.stream()
                .map(p -> new ProductSummaryResponse(p.getId(), p.getName(), p.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public void uploadProductImages(Long productId, List<MultipartFile> files) {
        Product product = getProductOrThrow(productId);

        // Upload files and set URLs (dummy for now)
        List<String> imageUrls = files.stream()
                .map(file -> "https://cdn.example.com/" + file.getOriginalFilename())
                .collect(Collectors.toList());

        product.getImageUrls().addAll(imageUrls);
        productRepository.save(product);
    }

    private Product getProductOrThrow(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
}

