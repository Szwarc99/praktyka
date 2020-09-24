package com.example.pkp.repos;

import com.example.pkp.entities.City;
import com.example.pkp.entities.Route;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface RouteRepository extends CrudRepository<Route,Long> {
    Route findByName(String name);
    ArrayList<Route> findAll();

}
