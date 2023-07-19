package com.example.spring_security_5.service;

import com.example.spring_security_5.entity.Roles;
import com.example.spring_security_5.entity.User;
import com.example.spring_security_5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }


        return  User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())// Assign roles to the user
                .build();

    }
}
