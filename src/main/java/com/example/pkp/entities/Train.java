package com.example.pkp.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToOne
    private Track track;

    public Train(String name,Track track) {
        this.name = name;
        this.track = track;
    }

    @OneToMany
    private List<Course> courses;

    public Train() {

    }
}
