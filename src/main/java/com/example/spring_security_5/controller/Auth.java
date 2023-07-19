package com.example.spring_security_5.controller;

import com.example.spring_security_5.dto.request.LoginRequest;
import com.example.spring_security_5.dto.request.SignUpRequest;
import com.example.spring_security_5.dto.response.LoginResponse;
import com.example.spring_security_5.dto.response.SignUpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface Auth {

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request);

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request);

}
