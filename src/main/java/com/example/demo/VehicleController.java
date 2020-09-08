package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class VehicleController {

    private final CarRepository carRepository;

    public VehicleController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    @RequestMapping("/cars")
    Collection<Car> cars(){
        return (Collection<Car>) this.carRepository.findAll();
    }

}
