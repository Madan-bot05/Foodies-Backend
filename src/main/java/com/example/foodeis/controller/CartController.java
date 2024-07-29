package com.example.foodeis.controller;


import com.example.foodeis.model.Cart;
import com.example.foodeis.model.CartItem;
import com.example.foodeis.request.AddCartItemRequest;
import com.example.foodeis.request.UpdateCartItemRequest;
import com.example.foodeis.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CartController {
    @Autowired
    private CartService cartService;

    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req, @RequestHeader("Authorization")String jwt) throws Exception {
        CartItem cartItem=cartService.addItemToCart(req,jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
    }

    @PutMapping("/cart/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody UpdateCartItemRequest req, @RequestHeader("Authorization")String jwt) throws Exception {
        CartItem cartItem=cartService.updateCartItem(req.getCartItemId(), req.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
    }

    @DeleteMapping ("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(@RequestBody UpdateCartItemRequest req, @RequestHeader("Authorization")String jwt, @PathVariable Long id) throws Exception {
        Cart cart=cartService.removeItemFromCart(id,jwt);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @DeleteMapping ("/car/clear")
    public ResponseEntity<Cart> clearCartItem( @RequestHeader("Authorization")String jwt, @PathVariable Long id) throws Exception {
        Cart cart=cartService.clearCart(jwt);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @GetMapping ("/cart")
    public ResponseEntity<Cart> findUserCart( @RequestHeader("Authorization")String jwt, @PathVariable Long id) throws Exception {
        Cart cart=cartService.findCartByUserId(jwt);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }



}
