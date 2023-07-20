package com.example.spring_security_5.controller;

import com.example.spring_security_5.dto.request.UpdateUserRoleRequest;
import com.example.spring_security_5.entity.User;
import com.example.spring_security_5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService personalInfoService) {
        this.userService = personalInfoService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedInfo = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInfo);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<User> getUser(@PathVariable Long uid) {
        User info = userService.getUser(uid);
        return ResponseEntity.ok(info);
    }

    @PutMapping("/{uid}")
    public ResponseEntity<User> updateUser(@PathVariable Long uid, @RequestBody User personalInfo) {
        User updatedInfo = userService.updateUser(uid, personalInfo);
        return ResponseEntity.ok(updatedInfo);
    }

    @PutMapping("/updateRole/{uid}")
    public ResponseEntity<User> updateRole(@PathVariable Long uid, @RequestBody UpdateUserRoleRequest roleRequest) {
        User updatedInfo = userService.updateUser(uid, roleRequest);
        return ResponseEntity.ok(updatedInfo);
    }


    @DeleteMapping("/{uid}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long uid) {
        userService.deleteUser(uid);
        return ResponseEntity.noContent().build();
    }
}

