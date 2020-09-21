package com.example.pkp.repos;

import com.example.pkp.entities.Driver;
import org.springframework.data.repository.CrudRepository;



public interface DriverRepository extends CrudRepository <Driver,Long> {
    Driver findByFirstnameAndSecondname(String firstname, String secondname);
}
