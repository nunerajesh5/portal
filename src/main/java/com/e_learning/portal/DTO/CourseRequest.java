package com.e_learning.portal.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {

    @Schema(description = "title of the course") @NotBlank(message = "title cannot be empty")
    private String title;

    @NotBlank(message = "description cannot be empty")
    private String description;

    @NotBlank(message = "give a valid Instructor Id")
    private Long instructorId;

    @NotBlank(message = "message cannot be empty")
    private String message;
}
