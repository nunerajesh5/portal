package com.e_learning.portal.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "username cannot be empty")
    private String fullName;

    @Email(message = "give a valid email address") @NotBlank(message = "email cannot be empty")
    private String email;

    @Size(max = 16, min = 8, message = "Password should contain 8 to 16 characters only") @NotBlank(message = "password cannot be empty")
    private String password;

    @NotBlank(message = "give a valid role")
    private String role;
}
