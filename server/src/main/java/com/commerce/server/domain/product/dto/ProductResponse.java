package com.commerce.server.domain.product.dto;

import com.commerce.server.domain.product.entity.ProductEntity;
import com.commerce.server.domain.product.entity.enums.Category;
import com.commerce.server.domain.product.entity.enums.Size;
import com.commerce.server.domain.product.entity.enums.SubCategory;

public record ProductResponse(
        Long id,
        String title,
        String content,
        double price,
        Category category,
        SubCategory subCategory,
        Size size,
        String image
) {
    public static ProductResponse from(ProductEntity product){
        return new ProductResponse(
                product.getId(),
                product.getTitle(),
                product.getContent(),
                product.getPrice(),
                product.getCategory(),
                product.getSubCategory(),
                product.getSize(),
                product.getImage()
        );
    }
}
