package com.Kafka_microservices.productservice.controller;

import com.Kafka_microservices.productservice.dto.ProductResponse;
import com.Kafka_microservices.productservice.exceptions.ProductNotFoundException;
import com.Kafka_microservices.productservice.model.Product;
import com.Kafka_microservices.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        Iterable<Product> retrieveAllProducts = productService.getAllProducts();
        log.info("Retrieved all products");
        return new ResponseEntity<>(retrieveAllProducts, HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<?> findSpecificProductById(@PathVariable Long productId) {
        try {
            Product retrieveProductById = productService.findSpecificProductById(productId);
            log.info("Retrieved product " + productId);
            return new ResponseEntity<>(retrieveProductById, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            log.info("Product " + productId + " does not exist.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product " + productId + " does not exist.");
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> productToBeCreated(@RequestBody Product createAProduct) {
        Product createProduct = productService.productToBeCreated(createAProduct);
        log.info("Successfully created a product");
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
    }


    // http://localhost:8081/api/inventory?skuCode={skuCode_name}
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> isInStock(@RequestParam List<String> skuCode) {
        return productService.isInStock(skuCode);
    }
}
