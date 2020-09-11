package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class VehicleController {

    @Autowired
    public VehicleController(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.carRepository.save(new Car(0,"BMW", "black"));
    }

    private final CarRepository carRepository;

    @RequestMapping("/cars")
    Collection<Car> cars(){
       return (Collection<Car>) this.carRepository.findAll();
    }

    @PostMapping("/cars")
    @ResponseStatus(HttpStatus.CREATED)
    public Car create(@RequestBody Car car) {
        return  carRepository.save(car);
    }

}
