package com.e_learning.portal.Controller;

import com.e_learning.portal.DTO.*;
import com.e_learning.portal.Service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Course API", description = "Manages course creation, updates, deletion, and retrieval.")
@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping
    @Operation(summary = "Creates a new course")
    public ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CourseRequest request) {
        return ResponseEntity.ok(courseService.createCourse(request));
    }

    @PreAuthorize("hasRole('ADMIN', 'INSTRUCTOR')")
    @PutMapping("/{id}")
    @Operation(summary = "Updates an existing course by ID")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequest request) {
        return ResponseEntity.ok(courseService.updateCourse(id, request));
    }

    @PreAuthorize("hasRole('ADMIN', 'INSTRUCTOR')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a course by ID")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping
    @Operation(summary = "Retrieves a list of all available courses")
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}