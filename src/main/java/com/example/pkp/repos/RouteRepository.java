package com.example.pkp.repos;

import com.example.pkp.entities.Route;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepository extends CrudRepository<Route,Long> {
    Route findByName(String name);
}
