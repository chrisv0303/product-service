package com.Kafka_microservices.productservice.service;

import com.Kafka_microservices.productservice.exceptions.ProductNotFoundException;
import com.Kafka_microservices.productservice.model.Product;
import com.Kafka_microservices.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product findSpecificProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(()
                -> new ProductNotFoundException("Product " + productId + " does not exist."));
    }

    public Product productToBeCreated(Product createAProduct) {
        return productRepository.save(createAProduct);
    }
}
