package com.example.foodeis.service;


import com.example.foodeis.model.Cart;
import com.example.foodeis.model.CartItem;
import com.example.foodeis.repository.CartItemRepository;
import com.example.foodeis.repository.CartRepository;
import com.example.foodeis.repository.FoodRespository;
import com.example.foodeis.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private FoodRespository foodRespository;


    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        return null;
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        return null;
    }

    @Override
    public CartItem updateCartItem(Long cartItemId, int quantity) throws Exception {
        return null;
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {
        return null;
    }

    @Override
    public Cart findCartById(Long cartItemId) throws Exception {
        return null;
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        return null;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        return null;
    }
}
