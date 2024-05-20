package com.naresh.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Transactional
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    private String name;
    private String description;
    private double rating;
    private int numberOfReviews;


    @JsonManagedReference
    @JsonIgnoreProperties("product")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_pid", referencedColumnName = "product_id")
    private List<ProductVariant> productVariants;

}
