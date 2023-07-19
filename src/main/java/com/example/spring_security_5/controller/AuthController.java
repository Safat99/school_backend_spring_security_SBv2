package com.example.spring_security_5.controller;

import com.example.spring_security_5.dto.request.LoginRequest;
import com.example.spring_security_5.dto.request.SignUpRequest;
import com.example.spring_security_5.dto.response.LoginResponse;
import com.example.spring_security_5.dto.response.SignUpResponse;
import com.example.spring_security_5.entity.User;
import com.example.spring_security_5.repository.UserRepository;
import com.example.spring_security_5.service.UserService;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements Auth {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<SignUpResponse> signUp(SignUpRequest request) {

        // Validate the signup request (e.g., check if the username is available)
        User user = userRepository.findByEmail(request.getEmail());

        if (user != null) {
            throw new UsernameNotFoundException("Already a user");
        }

        // encoding password
        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        // Create a new user using the signUpService implementation
        userService.createUser(
                    User.builder()
                            .username(request.getUsername())
                            .password(encodedPassword)
                            .email(request.getEmail())
                            .designation(request.getDesignation())
                            .extra_info(request.getExtra_info())
                        .build()
                    );

        //        SignUpResponse response = new SignUpResponse();
        //        response.setMessage("Signup successful");
        return ResponseEntity.ok(SignUpResponse.builder().message("SignUp successful").build());
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return null;
    }
}
