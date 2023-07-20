package com.Kafka_microservices.productservice;

import com.Kafka_microservices.productservice.model.Product;
import com.Kafka_microservices.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(ProductRepository productRepository) {
		return args -> {
			Product product = new Product();
			product.setSkuCode("ketchup");
			product.setQuantity(100);

			Product product1 = new Product();
			product1.setSkuCode("shampoo");
			product1.setQuantity(0);

			Product product2 = new Product();
			product2.setSkuCode("milk");
			product2.setQuantity(5);

			productRepository.save(product);
			productRepository.save(product1);
			productRepository.save(product2);
		};
	}
}
