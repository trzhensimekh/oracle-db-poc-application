package com.naya.oracledbpocapplication.controllers;

import com.naya.oracledbpocapplication.models.Product;
import com.naya.oracledbpocapplication.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(HttpSession session) {
        System.out.println(session.getId());
        return productService.findAllProducts();
    }

    @PostMapping(value="/products",consumes="application/json")
    public Product createProduct(@RequestBody Product product) {
        System.out.println(product);
        return productService.saveProduct(product);
    }
}
