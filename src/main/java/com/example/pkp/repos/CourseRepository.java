package com.example.pkp.repos;

import com.example.pkp.entities.City;
import com.example.pkp.entities.Course;
import com.example.pkp.entities.Driver;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course,Long> {
    Iterable<Course> findByDetailsContains(City start,City end);
}
