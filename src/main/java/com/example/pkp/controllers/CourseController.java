package com.example.pkp.controllers;

import com.example.pkp.entities.Course;
import com.example.pkp.entities.Stop;
import com.example.pkp.entities.Track;
import com.example.pkp.entities.Train;
import com.example.pkp.repos.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@Transactional
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;


    @GetMapping("/courses")
    public Iterable<Course> getCourses() {
        System.out.println(1);
        List<Stop> listOfStops = new ArrayList<Stop>();
        listOfStops.add(0,new Stop("Szczecin",0));
        listOfStops.add(1,new Stop("Stargard",1));
        System.out.println(2);
        LocalDateTime szDate = LocalDateTime.of(2020,9,17,11,34,0);
        LocalDateTime stDate = LocalDateTime.of(2020,9,17,12,34,0);
        List<LocalDateTime> listOfDates =new ArrayList<LocalDateTime>();
        listOfDates.add(0,szDate);
        listOfDates.add(1,stDate);
        System.out.println(3);

        Track track = new Track("szczecin-stargard", listOfStops);
        Train train = new Train("barbakan",track);
        Course course = new Course(train,listOfDates);
        this.courseRepository.save(course);
       // System.out.println(courseRepository.count());

        return this.courseRepository.findAll();
    }

    @PostMapping("/courses")
    public Course create(@RequestBody Course course) {
        return courseRepository.save(course);
    }

}
