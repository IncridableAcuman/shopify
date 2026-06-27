package com.commerce.server.domain.product.controller;

import com.commerce.server.domain.product.dto.ProductRequest;
import com.commerce.server.domain.product.dto.ProductResponse;
import com.commerce.server.domain.product.entity.enums.Category;
import com.commerce.server.domain.product.entity.enums.SubCategory;
import com.commerce.server.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @ModelAttribute ProductRequest request){
        return ResponseEntity.ok(productService.createProduct(request));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @GetMapping("/prices/{price}")
    public ResponseEntity<List<ProductResponse>> getProductsByPrice(@PathVariable double price){
        return ResponseEntity.ok(productService.getProductsByPrice(price));
    }
    @GetMapping("/categories/{category}/subcategories/{subCategory}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategoryAndSubCategory(@PathVariable Category category, @PathVariable SubCategory subCategory){
        return ResponseEntity.ok(productService.getProductsByCategoryAndSubCategory(category,subCategory));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProductById(id));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> editProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request){
        return ResponseEntity.ok(productService.editProduct(id,request));
    }
}
