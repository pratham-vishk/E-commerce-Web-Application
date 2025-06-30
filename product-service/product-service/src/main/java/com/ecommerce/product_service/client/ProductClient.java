package com.ecommerce.product_service.client;

import com.ecommerce.product_service.model.ProductAvailabilityRequest;
import com.ecommerce.product_service.model.ProductAvailabilityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @PostMapping("/api/products/check-availability")
    List<ProductAvailabilityResponse> checkAvailability(@RequestBody List<ProductAvailabilityRequest> request);
}
