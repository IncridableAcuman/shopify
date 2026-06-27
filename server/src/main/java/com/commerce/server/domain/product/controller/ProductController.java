package com.commerce.server.domain.product.controller;

import com.commerce.server.domain.product.dto.ProductRequest;
import com.commerce.server.domain.product.dto.ProductResponse;
import com.commerce.server.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @ModelAttribute ProductRequest request){
        return ResponseEntity.ok(productService.createProduct(request));
    }
}
