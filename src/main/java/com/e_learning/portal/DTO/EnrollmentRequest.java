package com.e_learning.portal.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRequest {

    @NotBlank(message = "give a valid courseId")
    private Long courseId;


}
