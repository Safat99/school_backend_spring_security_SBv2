package com.example.spring_security_5.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class SignUpResponse {

    private String message;
}
