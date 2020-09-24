package com.example.pkp.controllers;

import com.example.pkp.entities.*;
import com.example.pkp.repos.*;
import com.example.pkp.mapKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

import static com.example.pkp.mapKey.getKey;


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

            LocalDateTime szDate = LocalDateTime.of(2020, 9, 18, 11, 00);
            LocalDateTime stDate = LocalDateTime.of(2020, 9, 18, 11, 40);
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
    @GetMapping("/courses/fromTo")
    public Iterable<Course> findByRoute(@RequestParam String first, @RequestParam String second) {
        City firstCity = cityRepository.findByName(first);
        City secondCity = cityRepository.findByName(second);
        ArrayList<Route> allRoutes = new ArrayList<Route>();
        ArrayList<Route> routes = new ArrayList<Route>();
        Iterable<Course> courses = new ArrayList<Course>();

        allRoutes = routeRepository.findAll();
        for(int i =0;i<allRoutes.size();i++) {
            if(allRoutes.get(i).getCities().containsValue(firstCity) && allRoutes.get(i).getCities().containsValue(secondCity)) {
                if(getKey(allRoutes.get(i).getCities(),firstCity) < getKey(allRoutes.get(i).getCities(),secondCity)) {
                    routes.add(allRoutes.get(i));
                    courses = courseRepository.findByRoute(routes.get(i));
                }
            }
        }
        return courses;
    }

    @GetMapping("/courses/byDate")
    public Iterable<Course> findByDetailsContains(@RequestParam String dates) {
        ArrayList<Course> allCourses = courseRepository.findAll();
        ArrayList<Course> courses = new ArrayList<Course>();
        String[] stringDates= dates.split(";");
        LocalDate from = LocalDate.parse(stringDates[0]);
        LocalDate to = LocalDate.parse(stringDates[1]);
        LocalDateTime from2 = from.atStartOfDay();
        int p = Period.between(from,to).getDays();
        System.out.println(p);

        for(int i = 0; i<allCourses.size();i++) {
            for(int j =0;j<p;j++) {
                boolean b = allCourses.get(j).getDetails().values().contains(from2.plusDays(new Long(j)));
                System.out.println(b);
                if(b) {
                courses.add(allCourses.get(i));
                }
            }
        }
        return courses;
    }


    @GetMapping("/routes")
    public Iterable<Route> getRoutes(@RequestParam String test) {

       String[] tests = test.split(";");
        System.out.println(tests[0]);
        System.out.println(tests[1]);
        return routeRepository.findAll();
    }

    @PostMapping("/add")
    public Course createCourse(@RequestParam String trainName,
                               @RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String routeName,
                               @RequestParam String  times) {

        Train train = trainRepository.findByName(trainName);
        System.out.println(train.getName());
        Route route = routeRepository.findByName(routeName);
        System.out.println(route.getName());
        Driver driver = driverRepository.findByFirstnameAndSecondname(firstName,lastName);
        System.out.println(driver.getFirstname());

        String[] stringTimes= times.split(";");
        List<LocalDateTime> listOfTimes = new ArrayList<LocalDateTime>();
        for(int i=0;i<route.getCities().size();i++) {
            LocalDateTime stopTime = LocalDateTime.parse(stringTimes[i],DateTimeFormatter.ISO_DATE_TIME);
            listOfTimes.add(i,stopTime);

        }
        Course course = new Course(driver, train, route, listOfTimes);
        Map<Integer,City> cities = course.getRoute().getCities();

        for(int i =0;i<cities.size();i++) {
            course.getDetails().put(cities.get(i), listOfTimes.get(i));
        }
        return courseRepository.save(course);
    }


    @PostMapping("/delete")
    public void deleteCourse(@RequestParam int id) {
        Long lid = new Long(id);
        courseRepository.deleteById(lid);
    }

    /*@PostMapping("/courses/update")
    public Course updateCourse() {

    }*/

}
