package com.example.pkp.repos;

import com.example.pkp.entities.City;
import com.example.pkp.entities.Course;
import com.example.pkp.entities.Driver;
import com.example.pkp.entities.Route;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface CourseRepository extends CrudRepository<Course,Long> {
   ArrayList<Course> findByRoute(Route route);
   ArrayList<Course> findAll();
   ArrayList<Course> findByDetailsContains(String dates);
}
