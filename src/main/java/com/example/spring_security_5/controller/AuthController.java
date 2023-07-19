package com.example.spring_security_5.controller;

import com.example.spring_security_5.dto.request.LoginRequest;
import com.example.spring_security_5.dto.request.SignUpRequest;
import com.example.spring_security_5.dto.response.LoginResponse;
import com.example.spring_security_5.dto.response.SignUpResponse;
import com.example.spring_security_5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public class AuthController implements Auth {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<SignUpResponse> signUp(SignUpRequest request) {



//        SignUpResponse response = new SignUpResponse();
//        response.setMessage("Signup successful");
        return ResponseEntity.ok(SignUpResponse.builder().message("SignUp successful").build());
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return null;
    }
}
