package com.example.spring_security_5.dto.request;

import com.example.spring_security_5.entity.Roles;
import lombok.Data;

@Data
public class UpdateUserRoleRequest {

    private Roles newRole;
}
