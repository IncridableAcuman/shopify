package com.commerce.server.domain.product.service;

import com.commerce.server.domain.product.dto.ProductRequest;
import com.commerce.server.domain.product.dto.ProductResponse;
import com.commerce.server.domain.product.entity.ProductEntity;
import com.commerce.server.domain.product.entity.enums.Category;
import com.commerce.server.domain.product.entity.enums.SubCategory;
import com.commerce.server.domain.product.repository.ProductRepository;
import com.commerce.server.exception.CustomNotFoundException;
import com.commerce.server.util.file.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        saveProduct(product);
        return ProductResponse.from(product);
    }
    public List<ProductResponse> getProducts(){
        List<ProductEntity> products = productRepository.findAll();
        return products
                .stream()
                .map(ProductResponse::from).toList();
    }
    public ProductResponse getProductById(Long id){
        ProductEntity product = findProductById(id);
        return ProductResponse.from(product);
    }
    public List<ProductResponse> getProductsByPrice(double price){
        List<ProductEntity> products = productRepository.findByPrice(price);
        return products
                .stream()
                .map(ProductResponse::from).toList();
    }
    public List<ProductResponse> getProductsByCategoryAndSubCategory(Category category, SubCategory subCategory){
        List<ProductEntity> products = productRepository.findByCategoryAndSubCategory(category,subCategory);
        return products
                .stream()
                .map(ProductResponse::from).toList();
    }
    @Transactional
    public String deleteProductById(Long id){
        ProductEntity product = findProductById(id);
        productRepository.delete(product);
        return "Product deleted";
    }

    public ProductResponse editProduct(Long id,ProductRequest request){
        ProductEntity product = findProductById(id);
        Optional.ofNullable(request.getTitle()).ifPresent(product::setTitle);
        Optional.ofNullable(request.getContent()).ifPresent(product::setContent);
        Optional.of(request.getPrice()).ifPresent(product::setPrice);
        Optional.ofNullable(request.getCategory()).ifPresent(product::setCategory);
        Optional.ofNullable(request.getSubCategory()).ifPresent(product::setSubCategory);
        if (request.getImage() != null){
            product.setImage(fileUtil.saveFile(request.getImage()));
        }
        saveProduct(product);
        return ProductResponse.from(product);
    }

    public ProductEntity  findProductById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new CustomNotFoundException("Product not found by id"));}
    @Transactional
    public void saveProduct(ProductEntity  product){productRepository.save(product);}
}
