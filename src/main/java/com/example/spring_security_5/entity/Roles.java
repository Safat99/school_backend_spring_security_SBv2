package com.example.spring_security_5.entity;

public enum Roles {
    ADMIN,
    USER,
    GUEST;

    private String name;

    private String getName(){
        return name;
    }
}
