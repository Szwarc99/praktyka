package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, String> {
    List<Car> findByMarka(String marka);
}
