package com.example.pkp.entities;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
@Data
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany
    private List<Stop> stops;

    public Track() {

    }

    public List<Stop> sortList() {
        List<Stop> sortedByNum = this.stops;
        for(int i =0; i<this.stops.size();i++) {
            Stop stop = this.stops.get(i);
            sortedByNum.set(stop.getNum(),stop);
        }
        return sortedByNum;
    }
    public Track(String name, List<Stop> stops) {
        this.name = name;
        this.stops = stops;
    }

}
