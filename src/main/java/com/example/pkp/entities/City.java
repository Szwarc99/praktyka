package com.example.pkp.entities;

import java.util.List;
import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @OneToMany
    @JoinColumn(name = "city_id")
    private List<Stop> stopList;

    public City() {

    }

    public City(String name) {
        this.name = name;
    }

}
