package com.threetrack.service;

import com.threetrack.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductId(Integer productId);
    boolean addProduct(Product product);
    boolean upProduct(Product product);
    boolean deleteProduct(Integer productId);
}
