package com.example.pkp.entities;

import javax.persistence.*;

@Entity
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    int order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public int getOrder() {
        return order;
    }

    private City city;
    private Track track;

    @ManyToOne
    public City getCity() {
        return city;
    }

    @ManyToOne
    public Track getTrack() {
        return track;
    }

}
