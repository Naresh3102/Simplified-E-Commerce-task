package com.naresh.ecommerce.controller;

import com.naresh.ecommerce.model.Cart;
import com.naresh.ecommerce.model.CartItem;
import com.naresh.ecommerce.model.ProductVariant;
import com.naresh.ecommerce.repo.CartItemRepository;
import com.naresh.ecommerce.repo.CartRepository;
import com.naresh.ecommerce.repo.ProductVariantRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;


    @PostMapping("/{cartId}/items")
    public ResponseEntity<String> addItemToCart(@PathVariable Long cartId, @RequestBody CartItem cartItem) {
        Optional<Cart> optionalCart = cartRepository.findById((cartId));
        Cart cart;

        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        } else {
            cart = new Cart();
            cartRepository.save(cart);
        }

        cart.getCartItems().add(cartItem);
        cartRepository.save(cart);

        return ResponseEntity.ok("Item added to the cart");
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        return optionalCart.map(cart -> ResponseEntity.ok(cart.getCartItems())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{cartId}/items/{cartItemId}")
    public ResponseEntity<String> updateCartItem(@PathVariable Long cartId, @PathVariable Long cartItemId, @RequestBody CartItem updatedCartItem) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);

        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            for (CartItem cartItem : cart.getCartItems()) {
                if (cartItem.getCartItemId().equals(cartItemId)) {
                    cartItem.setQuantity(updatedCartItem.getQuantity());
                    cartRepository.save(cart);
                    return ResponseEntity.ok("Cart item updated");
                }
            }
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cartId}/items/{cartItemId}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            Optional<CartItem> optionalCartItem = cart.getCartItems().stream().filter(item -> item.getCartItemId().equals(cartItemId)).findFirst();
            if (optionalCartItem.isPresent()) {
                CartItem cartItem = optionalCartItem.get();
                cart.getCartItems().remove(cartItem);
                cartItemRepository.delete(cartItem);
                cartRepository.save(cart);
                return ResponseEntity.ok("Item removed from the cart");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

