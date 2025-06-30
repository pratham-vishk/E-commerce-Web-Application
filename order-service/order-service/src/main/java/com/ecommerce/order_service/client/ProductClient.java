package com.ecommerce.order_service.client;

import com.ecommerce.order_service.model.ProductSummaryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @PostMapping("/api/products/bulk")
    List<ProductSummaryResponse> getProducts(@RequestBody List<Long> productIds);
}
