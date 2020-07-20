package com.desafio.controller;

import com.desafio.exception.ResourceNotFoundException;
import com.desafio.model.Course;
import com.desafio.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/cursos")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("/curso")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @GetMapping("/curso/{id}")
    public Course getCourceById(@PathVariable(value = "id") Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
    }

    @PutMapping("/curso/{id}")
    public Course updateCourse(@PathVariable(value = "id") Long courseId,
                                       @RequestBody Course courseDetails) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("course", "id", courseId));

        course.setUniversityCod(courseDetails.getUniversityCod());
        course.setName(courseDetails.getName());

        Course updateCourse = courseRepository.save(course);
        return updateCourse;
    }

    @DeleteMapping("/curso/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable(value = "id") Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));

        courseRepository.delete(course);

        return ResponseEntity.ok().build();
    }
}
