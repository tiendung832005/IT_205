package com.data.session15.service;

import com.data.session15.model.dto.ProductDto;
import com.data.session15.model.entity.Product;
import com.data.session15.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> searchProducts(String keyword) {
        return productRepository.findByProductNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getAvailableProducts() {
        return productRepository.findByQuantityGreaterThan(0)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getNewProducts(int year) {
        return productRepository.findByYearMaking(year)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDto(product);
    }

    private ProductDto convertToDto(Product product) {
        return new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getProducer(),
                product.getYearMaking(),
                product.getExpireDate(),
                product.getQuantity(),
                product.getPrice()
        );
    }

    public List<ProductDto> getNewProducts() {
        int currentYear = java.time.LocalDate.now().getYear();
        return getNewProducts(currentYear);
    }
}