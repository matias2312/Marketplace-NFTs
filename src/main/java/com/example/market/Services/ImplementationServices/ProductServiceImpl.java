package com.example.market.Services.ImplementationServices;

import com.example.market.Services.ProductService;
import com.example.market.models.Client;
import com.example.market.models.Product;
import com.example.market.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    @Override
    public Product getProductById(Long Id){
        return productRepository.findById(Id).get();
    }

    @Override
    public void saveProduct(Product product){
        productRepository.save(product);
    }

}
