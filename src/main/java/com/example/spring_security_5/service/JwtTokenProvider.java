package com.example.spring_security_5.service;

import com.example.spring_security_5.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${apps.jwt.secret}")
    private String jwtSecret;

    @Value("${apps.jwt.expirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        // Generate JWT token from the user's authentication
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(((User) authentication.getPrincipal()).getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}