package com.commerce.server.domain.product.dto;

import com.commerce.server.domain.product.entity.enums.Category;
import com.commerce.server.domain.product.entity.enums.SubCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductRequest {
    @NotBlank(message = "Title is required")
    @Size(min = 3,max = 150,message = "Title must be between 3 and 150 character")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 5,max = 500,message = "Content must be between 3 and 150 character")
    private String content;

    @NotNull(message = "Price is required")
    private double price;

    @NotNull(message = "Category is required")
    private Category category;

    @NotNull(message = "Subcategory is required")
    private SubCategory subCategory;

    @NotNull(message = "Size is required")
    private com.commerce.server.domain.product.entity.enums.Size size;

    @NotNull(message = "Image is required")
    private MultipartFile image;
}
