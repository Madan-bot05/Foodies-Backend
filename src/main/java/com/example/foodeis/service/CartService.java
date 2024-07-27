package com.example.foodeis.service;

import com.example.foodeis.model.Cart;
import com.example.foodeis.model.CartItem;
import com.example.foodeis.request.AddCartItemRequest;

public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;
    public CartItem updateCartItem(Long cartItemId,int quantity) throws Exception;
    public Long calculateCartTotal(Cart cart) throws Exception;
    public Cart findCartById(Long cartItemId) throws Exception;
    public Cart findCartByUserId(Long userId) throws Exception;
    public Cart clearCart(Long userId) throws Exception;
}
