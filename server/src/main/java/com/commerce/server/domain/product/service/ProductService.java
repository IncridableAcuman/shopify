package com.commerce.server.domain.product.service;

import com.commerce.server.domain.product.dto.ProductRequest;
import com.commerce.server.domain.product.dto.ProductResponse;
import com.commerce.server.domain.product.entity.ProductEntity;
import com.commerce.server.domain.product.repository.ProductRepository;
import com.commerce.server.util.file.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final FileUtil fileUtil;

    public ProductResponse createProduct(ProductRequest request){
        ProductEntity product = new ProductEntity();
        product.setTitle(request.getTitle());
        product.setContent(request.getContent());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setSubCategory(request.getSubCategory());
        product.setSize(request.getSize());
        product.setImage(fileUtil.saveFile(request.getImage()));
        productRepository.save(product);
        return ProductResponse.from(product);
    }
}
