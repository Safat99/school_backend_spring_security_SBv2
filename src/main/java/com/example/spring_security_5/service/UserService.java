package com.example.spring_security_5.service;

import com.example.spring_security_5.entity.User;
import com.example.spring_security_5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository personalInfoRepository) {
        this.userRepository = personalInfoRepository;
    }

    public User createUser(User personalInfo) {
        return userRepository.save(personalInfo);
    }

    public User getUser(Long uid) {
        return userRepository.findById(uid)
                .orElseThrow(() -> new EntityNotFoundException("User with UID " + uid + " not found."));
    }

    public User updateUser(Long uid, User updatedInfo) {
        User existingInfo = getUser(uid);
        existingInfo.setEmail(updatedInfo.getEmail());
        existingInfo.setPassword(updatedInfo.getPassword());
        existingInfo.setUsername(updatedInfo.getUsername());
        existingInfo.setDesignation(updatedInfo.getDesignation());

        return userRepository.save(existingInfo);
    }

    public void deleteUser(Long uid) {
        userRepository.deleteById(uid);
    }
}
