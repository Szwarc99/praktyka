package com.example.pkp.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    List<Stop> stops;

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public City() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany
    @JoinColumn(name= "stop_ids")
    public List<Stop> getStops() {
        return stops;
    }

}
