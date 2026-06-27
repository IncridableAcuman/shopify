package com.commerce.server.domain.product.repository;

import com.commerce.server.domain.product.entity.ProductEntity;
import com.commerce.server.domain.product.entity.enums.Category;
import com.commerce.server.domain.product.entity.enums.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    Optional<ProductEntity> findByCategoryAndSubCategory(Category category, SubCategory subCategory);
    Optional<ProductEntity> findByPrice(double price);

}
