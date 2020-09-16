package com.example.pkp.controllers;

import com.example.pkp.entities.Course;
import com.example.pkp.repos.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses")
    public Collection<Course> courses() {
        return (Collection<Course>) this.courseRepository.findAll();
    }

    @PostMapping("/courses")
    public Course create(@RequestBody Course course) {
        return courseRepository.save(course);
    }

}
