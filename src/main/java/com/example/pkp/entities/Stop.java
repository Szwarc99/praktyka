package com.example.pkp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Stop {

    @Id
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;



}
