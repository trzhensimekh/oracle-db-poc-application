package com.naya.oracledbpocapplication.services;

import com.naya.oracledbpocapplication.models.Product;
import com.naya.oracledbpocapplication.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
