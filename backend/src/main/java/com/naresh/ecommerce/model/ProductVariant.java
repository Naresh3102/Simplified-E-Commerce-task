package com.naresh.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@Entity
@Data
@Transactional
@NoArgsConstructor
@Table(name = "product_variant")
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_id")
    private Long variantId;

    @JsonIgnoreProperties("product_variant")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_color_id")
    private Color color;

    private String size;
    private double price;
    private int stock;

    private String imageUrl;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_pid")
    private Product product;
}
