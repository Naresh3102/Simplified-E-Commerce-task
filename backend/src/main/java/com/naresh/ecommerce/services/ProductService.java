package com.naresh.ecommerce.services;

import com.naresh.ecommerce.model.Product;
import com.naresh.ecommerce.model.ProductVariant;
import com.naresh.ecommerce.repo.ProductRepository;
import com.naresh.ecommerce.repo.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    public Product createProductWithVariants(@RequestBody Product product) {
        productRepository.save(product);
        for (ProductVariant variant : product.getProductVariants()) {
            System.out.println(variant);
            variant.setProduct(product);
            productVariantRepository.save(variant);
        }
        return product;
    }
}
