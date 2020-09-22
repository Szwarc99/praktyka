package com.example.pkp.controllers;

import com.example.pkp.entities.*;
import com.example.pkp.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    boolean init = false;





    @GetMapping("/courses")
    public Iterable<Course> getCourses() {

        if(init == false) {
            cityRepository.save(new City("Szczecin"));
            cityRepository.save(new City("Stargard"));
            driverRepository.save(new Driver("Jan", "Kowalski"));
            System.out.println(1);

            Map<Integer, City> listOfCities = new HashMap<Integer, City>();
            listOfCities.put(0, cityRepository.findByName("Szczecin"));
            listOfCities.put(1, cityRepository.findByName("Stargard"));
            System.out.println(2);

            LocalDateTime szDate = LocalDateTime.of(2020, 9, 18, 11, 00, 0);
            LocalDateTime stDate = LocalDateTime.of(2020, 9, 18, 11, 40, 0);
            List<LocalDateTime> listOfDates = new ArrayList<LocalDateTime>();
            listOfDates.add(0, szDate);
            listOfDates.add(1, stDate);
            System.out.println(3);

            routeRepository.save(new Route("szczecin-stargard", listOfCities));
            Map<Integer, City> listOfCities2 = new HashMap<Integer, City>();
            listOfCities2.put(0, cityRepository.findByName("Stargard"));
            listOfCities2.put(1, cityRepository.findByName("Szczecin"));
            System.out.println(3.5);

            routeRepository.save(new Route("stargard-szczecin", listOfCities2));

            trainRepository.save(new Train("SNOWPIERCER"));
            System.out.println(4 + trainRepository.findByName("SNOWPIERCER").getName());

            Driver driver = driverRepository.findByFirstnameAndSecondname("Jan", "Kowalski");
            Train train = trainRepository.findByName("SNOWPIERCER");
            Route route = routeRepository.findByName("szczecin-stargard");
            System.out.println("driv ID" + driver.getId());
            System.out.println("train ID" + train.getId());
            System.out.println("route ID" + route.getId());

            courseRepository.save(new Course(driver, train, route, listOfDates));
            System.out.println(5);
            init = true;
        }
        return courseRepository.findAll();
    }


    @GetMapping("/routes")
    public Iterable<Route> getRoutes() {
        return routeRepository.findAll();
    }

    @PostMapping("/courses/add")
    public Course createCourse(@RequestParam String trainName,
                         @RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam String routeName) {
        System.out.println("siema");
        List<LocalDateTime> times = new ArrayList<LocalDateTime>();
        System.out.println("siema2");
        times.add(LocalDateTime.of(2020,9,18,11,00,00));
        times.add(LocalDateTime.of(2020,9,18,11,35,00));
        System.out.println("siema3");

        Train train = trainRepository.findByName(trainName);
        System.out.println(train.getName());
        Route route = routeRepository.findByName(routeName);
        System.out.println(route.getName());
        Driver driver = driverRepository.findByFirstnameAndSecondname(firstName,lastName);
        System.out.println(driver.getFirstname());




        List<LocalDateTime> listOfDates = new ArrayList<LocalDateTime>();
        for(int i =0;i<times.size();i++) {
            LocalDateTime stopTime = times.get(i);
            listOfDates.add(i,stopTime);
        }
        Course course = new Course(driver, train, route, listOfDates);
        Map<Integer,City> cities = course.getRoute().getCities();

        for(int i =0;i<cities.size();i++) {
            course.getDetails().put(cities.get(i), listOfDates.get(i));
        }
        return courseRepository.save(course);
    }


    @PostMapping("/courses/delete")
    public void deleteCourse(@RequestParam int id) {
        Long lid = new Long(id);
        courseRepository.deleteById(lid);
    }

    /*@PostMapping("/courses/update")
    public Course updateCourse() {

    }*/

}
