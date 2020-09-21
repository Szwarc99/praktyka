package com.example.pkp.repos;

import com.example.pkp.entities.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository <City,Long> {
    City findByName(String name);
}
