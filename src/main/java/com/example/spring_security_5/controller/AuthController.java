package com.example.spring_security_5.controller;

import com.example.spring_security_5.dto.request.LoginRequest;
import com.example.spring_security_5.dto.request.SignUpRequest;
import com.example.spring_security_5.dto.response.LoginResponse;
import com.example.spring_security_5.dto.response.SignUpResponse;
import org.springframework.http.ResponseEntity;

public class AuthController implements Auth {
    @Override
    public ResponseEntity<SignUpResponse> signUp(SignUpRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return null;
    }
}
