package com.e_learning.portal.Service;

import com.e_learning.portal.DTO.*;
import com.e_learning.portal.Entity.Course;
import com.e_learning.portal.Entity.User;
import com.e_learning.portal.Exception.ResourceNotFoundException;
import com.e_learning.portal.Repository.CourseRepository;
import com.e_learning.portal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public CourseResponse createCourse(CourseRequest request) {

        User instructor= userRepository.findById(request.getInstructorId()).orElseThrow(()->new UsernameNotFoundException("Instructor not found"));
        Course course = Course.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .instructor(instructor)
                .build();

        courseRepository.save(course);

        return mapToResponse(course);
    }

    public CourseResponse updateCourse(Long courseId, CourseRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());

        courseRepository.save(course);

        return mapToResponse(course);
    }

    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        courseRepository.delete(course);
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private CourseResponse mapToResponse(Course course) {
        return new CourseResponse(course.getId(), course.getDescription());
    }
}
