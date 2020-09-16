package com.example.pkp.entities;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Stop {

    @Id
    private Long id;
    private String name;
    //stop order
    private int num;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    private Track track;
}
