package com.naresh.ecommerce.controller;

import com.naresh.ecommerce.model.ProductVariant;
import com.naresh.ecommerce.repo.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/variants")
public class ProductVariantController {

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @GetMapping
    public List<ProductVariant> getVariants() {
        return productVariantRepository.findAll();
    }
}
