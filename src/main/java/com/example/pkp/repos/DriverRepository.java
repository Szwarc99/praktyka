package com.example.pkp.repos;

import com.example.pkp.entities.Driver;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface DriverRepository extends CrudRepository<Driver, Long> {
    Driver findByFirstnameAndSecondname(String firstname, String secondname);
    //Driver findById(Long id);
}
