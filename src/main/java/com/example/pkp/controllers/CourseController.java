package com.example.pkp.controllers;

import com.example.pkp.entities.*;
import com.example.pkp.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Transactional
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private TrainRepository trainRepository;



    @GetMapping("/courses")
    public Iterable<Course> getCourses() {

        cityRepository.save(new City("Szczecin"));
        cityRepository.save(new City("Stargard"));
        driverRepository.save(new Driver("Jan", "Kowalski"));
        System.out.println(1);

        Map<Integer,City> listOfCities = new HashMap<Integer,City>();
        listOfCities.put(0,cityRepository.findByName("Szczecin"));
        listOfCities.put(1,cityRepository.findByName("Stargard"));
        System.out.println(2);

        LocalDateTime szDate = LocalDateTime.of(2020,9,18,11,00,0);
        LocalDateTime stDate = LocalDateTime.of(2020,9,18,11,40,0);
        List<LocalDateTime> listOfDates =new ArrayList<LocalDateTime>();
        listOfDates.add(0,szDate);
        listOfDates.add(1,stDate);
        System.out.println(3);

        routeRepository.save(new Route("szczecin-stargard", listOfCities));

        trainRepository.save(new Train("SNOWPIERCER"));
        System.out.println(4 + trainRepository.findByName("SNOWPIERCER").getName());

        Driver driver = driverRepository.findByFirstnameAndSecondname("Jan","Kowalski");
        Train train = trainRepository.findByName("SNOWPIERCER");
        Route route = routeRepository.findByName("szczecin-stargard");
        System.out.println("driv ID" + driver.getId());
        System.out.println("train ID" + train.getId());
        System.out.println("route ID" + route.getId());

        courseRepository.save(new Course(driver,train, route,listOfDates));
        System.out.println(5);


        return courseRepository.findAll();
    }

    @PostMapping("/courses")
    public Course create(@RequestBody Course course) {
        return courseRepository.save(course);
    }

}
