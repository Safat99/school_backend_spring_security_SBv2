package com.example.spring_security_5.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class PersonalInfoUserRoles {

    @EmbeddedId
    private PersonalInfoUserRolesId id;

    // Default constructor
    public PersonalInfoUserRoles() {
    }
}
