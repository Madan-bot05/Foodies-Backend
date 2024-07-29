package com.example.foodeis.service;


import com.example.foodeis.model.Cart;
import com.example.foodeis.model.CartItem;
import com.example.foodeis.model.Food;
import com.example.foodeis.model.User;
import com.example.foodeis.repository.CartItemRepository;
import com.example.foodeis.repository.CartRepository;
import com.example.foodeis.repository.FoodRespository;
import com.example.foodeis.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImplementation implements CartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private FoodService foodService;


    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user =userService.findUserByJwtToken(jwt);
        Food food=foodService.findFoodById(req.getFoodId());
        Cart cart=cartRepository.findByCustomerId(user.getId());

        for (CartItem cartItem: cart.getItem()){
            if(cartItem.getFood().equals(food)){
                int quantity=cartItem.getQuantity()+req.getQuantity();
                return updateCartItem(cartItem.getId(),quantity);
            }
        }
        CartItem cartItem=new CartItem();
        cartItem.setFood(food);
        cartItem.setQuantity(req.getQuantity());
        cartItem.setCart(cart);
        cartItem.setIngredients(req.getIngredients());
        cartItem.setTotalPrice(req.getQuantity()+food.getPrice());

        CartItem newCartItem=cartItemRepository.save(cartItem);
        cart.getItem().add(newCartItem);

        return newCartItem;
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user =userService.findUserByJwtToken(jwt);
        Cart cart=cartRepository.findByCustomerId(user.getId());
        Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()){
            throw new Exception("CartItem not found");
        }
        CartItem item=cartItem.get();
        cart.getItem().remove(item);

        return cartRepository.save(cart);
    }

    @Override
    public CartItem updateCartItem(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()){
            throw new Exception("CartItem not found");
        }
        CartItem item=cartItem.get();
        item.setQuantity(quantity);
        item.setTotalPrice((item.getFood().getPrice()*quantity));
        return cartItemRepository.save(item);
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {
        Long total=0L;
        for (CartItem cartItem: cart.getItem()){
            total+=cartItem.getFood().getPrice()*cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long cartItemId) throws Exception {
        Optional<Cart> cartItem=cartRepository.findById(cartItemId);
        if(cartItem.isEmpty()){
            throw new Exception("CartItem not found");
        }

        return cartItem.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {

        return cartRepository.findByCustomerId(userId);
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart=cartRepository.findByCustomerId(userId);
        cart.getItem().clear();
        return cartRepository.save(cart);
    }
}
