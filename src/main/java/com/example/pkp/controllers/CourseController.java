package com.example.pkp.controllers;

import com.example.pkp.entities.*;
import com.example.pkp.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


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

        if(!init ) {
            cityRepository.save(new City("Szczecin"));
            cityRepository.save(new City("Stargard"));
            cityRepository.save(new City("Krzyz"));
            cityRepository.save(new City("Poznan"));
            driverRepository.save(new Driver("Jan", "Kowalski"));
            driverRepository.save(new Driver("Marek","Nowak"));
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
        ArrayList<Route> allRoutes = routeRepository.findAll();
        ArrayList<Route> routes = new ArrayList<Route>();
        Iterable<Course> courses = new ArrayList<Course>();

        for(int i =0;i<allRoutes.size();i++) {
            if(allRoutes.get(i).getCities().containsValue(firstCity) && allRoutes.get(i).getCities().containsValue(secondCity)) {
                System.out.println("first if");
                if(getKey(allRoutes.get(i).getCities(),firstCity) < getKey(allRoutes.get(i).getCities(),secondCity)) {
                    System.out.println("second if");
                    routes.add(allRoutes.get(i));
                    int index = routes.indexOf(allRoutes.get(i));
                    System.out.println(routes.get(index));
                    courses = courseRepository.findByRoute(allRoutes.get(index));
                    //System.out.println(routes.get(i).getName());
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
        System.out.println(from);
        int p = Period.between(from,to).getDays();
        System.out.println(p);

        for(Course it:allCourses) {
            List<LocalDate> dateList = new ArrayList<LocalDate>();
            for (int i = 0; i < it.getTimeTable().size(); i++) {
                dateList.add(i, it.getTimeTable().get(i).toLocalDate());
            }
            for (int i = 0; i < dateList.size(); i++) {
                LocalDate b = from.plusDays(i);
                System.out.println(b);
                boolean check = b.equals(dateList.get(i));
                System.out.println(check);
                if(check) {
                    courses.add(it);
                }
            }
        }
        return courses;
    }


    @GetMapping("/routes")
    public Iterable<Route> getRoutes() {
        return routeRepository.findAll();
    }

    @PostMapping("/courses/add")
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


    @GetMapping("/courses/delete")
    public void deleteCourse(@RequestParam int id) {
        Long lid = new Long(id);
        courseRepository.deleteById(lid);
    }

    @PostMapping("/courses/update")
    public void updateCourse(@RequestParam Long CourseID,
                             @RequestParam String trainName,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String routeName,
                             @RequestParam String  times) {

        boolean isCourse = courseRepository.existsById(CourseID);
        System.out.println(isCourse);
        if(isCourse) {
            Train train = trainRepository.findByName(trainName);
            System.out.println(train.getName());
            Route route = routeRepository.findByName(routeName);
            System.out.println(route.getName());
            Driver driver = driverRepository.findByFirstnameAndSecondname(firstName, lastName);
            System.out.println(driver.getFirstname());

            String[] stringTimes = times.split(";");
            List<LocalDateTime> listOfTimes = new ArrayList<LocalDateTime>();
            for (int i = 0; i < route.getCities().size(); i++) {
                LocalDateTime stopTime = LocalDateTime.parse(stringTimes[i], DateTimeFormatter.ISO_DATE_TIME);
                listOfTimes.add(i, stopTime);

            }
            Course course = new Course(driver, train, route, listOfTimes);
            Map<Integer, City> cities = course.getRoute().getCities();

            for (int i = 0; i < cities.size(); i++) {
                course.getDetails().put(cities.get(i), listOfTimes.get(i));
            }
            course.setId(CourseID);
            courseRepository.save(course);
        }
        else {
        System.out.println("Cannot update, wrong Course ID");
        }
    }
    @PostMapping("/cities/add")
    public void addCity(@RequestParam String cityName) {
        if(!cityRepository.existsByName(cityName)) {
            cityRepository.save(new City(cityName));
        }
        else System.out.println("cannot have 2 cities with same names");
    }

    @GetMapping("/cities")
    public Iterable<City> getCities() {
        return cityRepository.findAll();
    }
    @PostMapping("/cities/delete")
    public void deleteCity(@RequestParam String cityName) {
        cityRepository.deleteByName(cityName);
    }

    @PostMapping("/routes/add")
    public void addRoute(@RequestParam String routeName, @RequestParam String ... cities) {
        if(!routeRepository.existsByName(routeName)) {
            Map<Integer, City> mapOfCities = new HashMap<Integer, City>();
            for (int i = 0; i < cities.length; i++) {
                mapOfCities.put(i, cityRepository.findByName(cities[i]));
            }
            Route route = new Route(routeName, mapOfCities);
            routeRepository.save(route);
        }
        else System.out.println("Cannot have 2 routes with the same name");
    }
    @GetMapping("routes/delete")
    public void deleteRoute(@RequestParam String routeName) {
        routeRepository.deleteByName(routeName);
    }

    @PostMapping("/drivers/add")
    public void addDriver(@RequestParam String firstName, @RequestParam String secondName) {
        driverRepository.save(new Driver(firstName,secondName));
    }
    @GetMapping("/drivers")
    public Iterable<Driver> getDrivers() {
        return driverRepository.findAll();
    }
    @GetMapping("/drivers/delete")
    public void deleteDriverById(Long id) {
        driverRepository.deleteById(id);
    }

    @GetMapping("/trains")
    public Iterable<Train> getTrains() {
        return trainRepository.findAll();
    }
    @PostMapping("/trains/add")
    public void addTrain(String trainName) {
        if(!trainRepository.existsByName(trainName)) {
            trainRepository.save(new Train(trainName));
        }
        else System.out.println("Cannot have 2 trains with the same name");
    }
    @GetMapping("/trains/delete")
    public void deleteTrain(String trainName) {
        trainRepository.deleteByName(trainName);
    }



}
