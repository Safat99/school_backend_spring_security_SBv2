package com.example.spring_security_5.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class PersonalInfoUserRolesId implements Serializable {

    @Column(name = "personal_info_id")
    private Long personalInfoId;

    @Column(name = "user_roles_id")
    private Long userRolesId;

    // Default constructor
    public PersonalInfoUserRolesId() {
    }
}