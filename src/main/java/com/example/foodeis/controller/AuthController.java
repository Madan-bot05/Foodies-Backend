package com.example.foodeis.controller;


import com.example.foodeis.config.JwtProvider;
import com.example.foodeis.model.Cart;
import com.example.foodeis.model.User;
import com.example.foodeis.repository.CartRepository;
import com.example.foodeis.repository.UserRepository;
import com.example.foodeis.response.AuthResponse;
import com.example.foodeis.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;
    @Autowired
    private CartRepository cartRepository;


    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {

        User isEmailUser = userRepository.findByEmail(user.getEmail());
        if(isEmailUser != null){
            throw new Exception("Email is already used with another account");
        }
        User createUser = new User();
        createUser.setEmail(user.getEmail());
        createUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createUser.setFullName(user.getFullName());
        createUser.setRole(user.getRole());

        User savedUser=userRepository.save(createUser);

        Cart cart=new Cart();
        cart.setCustomer(savedUser);


        return  null;
    }

//    @PostMapping("/hello")
//    public String hello(){
//        return "Hello World!";
//    }
}
