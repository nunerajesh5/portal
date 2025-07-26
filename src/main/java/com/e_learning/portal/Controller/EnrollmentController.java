package com.e_learning.portal.Controller;

import com.e_learning.portal.DTO.*;
import com.e_learning.portal.Service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Enrollment API", description = "Handles student course enrollments and retrieves enrolled courses.")
@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping
    @Operation(summary = "Enrolls a student in a course")
    public ResponseEntity<String> enroll(@Valid @RequestBody EnrollmentRequest request) {
        System.out.println(request);
        return ResponseEntity.ok(enrollmentService.enrollInCourse(request));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/my-courses")
    @Operation(summary = "Fetches all courses the student is enrolled in")
    public ResponseEntity<List<EnrollmentResponse>> getMyEnrollments() {
        return ResponseEntity.ok(enrollmentService.getMyEnrollments());
    }
}
