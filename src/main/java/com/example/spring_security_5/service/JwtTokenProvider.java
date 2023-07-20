package com.example.spring_security_5.service;

import com.example.spring_security_5.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        User principal = (User) authentication.getPrincipal();

        // Add user role as a claim
        Claims claims = Jwts.claims().setSubject(principal.getUsername());
        claims.put("role", principal.getRole().name());

        return Jwts.builder()
//                .setSubject(((User) authentication.getPrincipal()).getUsername())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // Validate and parse JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            // Invalid token
            return false;
        }
    }

    // Get Authentication object from JWT token
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = getUserDetailsFromToken(token);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userDetails.getAuthorities().iterator().next().getAuthority()));

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    // Extract UserDetails from JWT token
    public UserDetails getUserDetailsFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        String role = claims.get("role", String.class);
        // You can extract any other user-related information from the claims, if needed.
        return new org.springframework.security.core.userdetails.User(username, "", AuthorityUtils.createAuthorityList(role));
    }

    // Resolve JWT token from the request header
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}