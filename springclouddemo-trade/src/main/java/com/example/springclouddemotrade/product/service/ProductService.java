package com.example.springclouddemotrade.product.service;

import com.example.springclouddemotrade.product.entity.Category;
import com.example.springclouddemotrade.product.entity.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Category> listCategory();

    List<Product> listProduct(Long categoryId);

    List<Product> searchProduct(int pageNumber, int pageSize, String searchContent) throws IOException;

    Product productDetail(Long id);
}
