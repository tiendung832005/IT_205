package com.data.session07.service;

import com.data.session07.entity.ProductDetail;
import com.data.session07.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    public List<ProductDetail> getAllProductDetails() {
        return productDetailRepository.findAll();
    }

    public ProductDetail createProductDetail(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    public ProductDetail updateProductDetail(Long id, ProductDetail productDetail) {
        productDetail.setProductDetailId(id);
        return productDetailRepository.save(productDetail);
    }

    public void deleteProductDetail(Long id) {
        productDetailRepository.deleteById(id);
    }
}
