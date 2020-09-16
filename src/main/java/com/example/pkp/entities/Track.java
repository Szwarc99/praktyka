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

    public List<Stop> sortList(List<Stop> stops) {
        List<Stop> sortedByNum = stops;
        for(int i =0; i<stops.size();i++) {
            Stop stop = stops.get(i);
            sortedByNum.set(stop.getNum(),stop);
        }
        return sortedByNum;
    }
    public Track(Long id, String name, List<Stop> stops) {
        this.id = id;
        this.name = name;
        this.stops = stops;
    }

}
