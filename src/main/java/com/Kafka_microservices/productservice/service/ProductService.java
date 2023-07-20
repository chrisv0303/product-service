package com.Kafka_microservices.productservice.service;

import com.Kafka_microservices.productservice.dto.ProductResponse;
import com.Kafka_microservices.productservice.exceptions.ProductNotFoundException;
import com.Kafka_microservices.productservice.model.Product;
import com.Kafka_microservices.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<ProductResponse> isInStock(List<String> skuCode) {
        return productRepository.findBySkuCodeIn(skuCode).stream()
                .map(product ->
                    ProductResponse.builder()
                            .skuCode(product.getSkuCode())
                            .isInStock(product.getQuantity() > 0)
                            .build()
                ).toList();
    }
}
