package com.example.pkp.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@Entity
public class Train {
    @Id
    private Long id;

    @OneToOne
    private Track track;

    @OneToMany
    private List<Course> courses;
}
