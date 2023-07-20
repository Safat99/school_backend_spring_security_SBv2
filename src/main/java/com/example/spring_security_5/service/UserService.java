package com.example.spring_security_5.service;

import com.example.spring_security_5.entity.Roles;
import com.example.spring_security_5.entity.User;
import com.example.spring_security_5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public User getUser(Long uid) {
        return userRepository.findById(uid)
                .orElseThrow(() -> new EntityNotFoundException("User with UID " + uid + " not found."));
    }


    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and #user.email == principal.email)" )
    public User updateUser(Long uid, User updatedInfo) {
        User existingInfo = getUser(uid);
        existingInfo.setEmail(updatedInfo.getEmail());
        existingInfo.setPassword(updatedInfo.getPassword());
        existingInfo.setUsername(updatedInfo.getUsername());
        existingInfo.setDesignation(updatedInfo.getDesignation());

        return userRepository.save(existingInfo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public User updateRole(Long uid, Roles role) {
        User user = getUser(uid);
        user.setRole(role);

        return userRepository.save(user);
    }


    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long uid) {
        userRepository.deleteById(uid);
    }
}
