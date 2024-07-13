package com.example.foodeis.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
