package com.example.market.Services;

import com.example.market.models.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();
    public Product getProductById(Long Id);
    public void saveProduct(Product product);

}
