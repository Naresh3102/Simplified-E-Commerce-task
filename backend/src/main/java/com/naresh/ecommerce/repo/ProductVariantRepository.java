package com.naresh.ecommerce.repo;

import com.naresh.ecommerce.model.Product;
import com.naresh.ecommerce.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
}
