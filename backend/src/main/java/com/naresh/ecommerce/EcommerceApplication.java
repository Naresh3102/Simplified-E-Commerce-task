package com.naresh.ecommerce;

import com.naresh.ecommerce.model.Product;
import com.naresh.ecommerce.model.ProductVariant;
import com.naresh.ecommerce.repo.ProductRepository;
import com.naresh.ecommerce.repo.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductVariantRepository productVariantRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}


}
