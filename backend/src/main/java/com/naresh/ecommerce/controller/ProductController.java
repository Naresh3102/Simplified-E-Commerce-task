package com.naresh.ecommerce.controller;

import com.naresh.ecommerce.model.Product;
import com.naresh.ecommerce.model.ProductVariant;
import com.naresh.ecommerce.repo.ProductRepository;
import com.naresh.ecommerce.repo.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @PostMapping
    public ResponseEntity<String> createProductWithVariants(@RequestBody Product product) {
//        for (ProductVariant variant : product.getProductVariants()) {
//            variant.setProduct((product));
//            productVariantRepository.save(variant);
//        }
        productVariantRepository.saveAll(product.getProductVariants());

        productRepository.save(product);
        return ResponseEntity.ok("Data saved");
    }

    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{productId}")
    public Optional<Product> getProductById(@PathVariable Long productId) {
        return productRepository.findById(productId);
    }
}
