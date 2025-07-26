package com.e_learning.portal.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotBlank(message = "give a valid email")
    private String email;

    @Size(max = 16, min = 8, message = "Password should contain 8 to 16 characters only") @NotBlank(message = "password cannot be empty")
    private String password;


}
