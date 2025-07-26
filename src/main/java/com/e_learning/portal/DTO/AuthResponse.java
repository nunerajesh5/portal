package com.e_learning.portal.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String JWTtoken;
    private Long id;
    private String role;
    }
