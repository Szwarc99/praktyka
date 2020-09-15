package com.example.pkp.entities;

import javax.persistence.*;
import java.util.List;
@Entity
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    public Track() {}

    public Track(Long id, List<Stop> stops) {
        this.id = id;
        this.stops = stops;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public Long getId() {
        return id;
    }

    private  List<Stop> stops;

    @OneToMany
    public List<Stop> getStops() {
        return stops;
    }


}
