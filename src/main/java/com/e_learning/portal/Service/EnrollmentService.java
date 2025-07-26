package com.e_learning.portal.Service;

import com.e_learning.portal.DTO.*;
import com.e_learning.portal.Entity.*;
import com.e_learning.portal.Exception.AlreadyEnrolledException;
import com.e_learning.portal.Exception.ResourceNotFoundException;
import com.e_learning.portal.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public String enrollInCourse(EnrollmentRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if(enrollmentRepository.findByStudentIdAndCourseId(student.getId(), course.getId()).isPresent()){
            System.out.println(student.getId());
            System.out.println(course.getId());
            throw new AlreadyEnrolledException("Already enrolled in this course");
        }


        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .enrolledAt(LocalDateTime.now())
                .build();

        enrollmentRepository.save(enrollment);
        return "Enrollment successful";
    }

    public List<EnrollmentResponse> getMyEnrollments() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Enrollment> enrollment = enrollmentRepository.findAllByStudent(student);

        return enrollment.stream()
                .map(e -> new EnrollmentResponse(
                        e.getCourse().getId(),
                        e.getCourse().getDescription()
                ))
                .collect(Collectors.toList());
    }
}
