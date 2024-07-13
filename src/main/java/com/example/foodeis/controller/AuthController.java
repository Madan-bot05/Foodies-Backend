package com.example.foodeis.controller;


import com.example.foodeis.config.JwtProvider;
import com.example.foodeis.model.Cart;
import com.example.foodeis.model.USER_ROLE;
import com.example.foodeis.model.User;
import com.example.foodeis.repository.CartRepository;
import com.example.foodeis.repository.UserRepository;
import com.example.foodeis.request.LoginRequest;
import com.example.foodeis.response.AuthResponse;
import com.example.foodeis.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {

        try {
            System.out.println("Received signup request for email: " + user.getEmail());

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

        Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register Sucess");
        authResponse.setRole(savedUser.getRole());

        return  new ResponseEntity<>(authResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    public ResponseEntity<AuthResponse> signIn(@RequestBody User user) throws Exception {
//        User isEmailUSer=userRepository.findByEmail(user.getEmail());
//        if(isEmailUSer==null){
//            throw new Exception("Email is not found");
//        }
//        return null;
//    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest req){
        String username= req.getEmail();
        String password=req.getPassword();
        Authentication authentication=authenticate(username,password);

        Collection<?extends GrantedAuthority> authorities=authentication.getAuthorities();
        String role=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        String jwt=jwtProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Sign in Success");

        authResponse.setRole(USER_ROLE.valueOf(role));

        return  new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails=customerUserDetailsService.loadUserByUsername(username);
        if(userDetails==null){
            throw new BadCredentialsException("Invalid Username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
