package com.example.pkp.entities;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    //stop order
    private int num;

    public Stop(String name, int num) {
        this.name = name;
        this.num = num;
    }

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    private Track track;

    public Stop() {

    }
}
