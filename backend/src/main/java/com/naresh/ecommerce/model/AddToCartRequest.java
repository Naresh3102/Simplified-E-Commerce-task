package com.naresh.ecommerce.model;

import lombok.Data;

@Data
public class AddToCartRequest {
    private Long variantId;
    private int quantity;
}
