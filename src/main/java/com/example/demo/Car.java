package com.example.demo;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
public class Car {

    public Car(){

    }
    public Car(String marka)
    {
        this.id = id;
        this.marka = marka;
    }

    @javax.persistence.Id
    @Id
    int id;

    @Column
    private String marka;
}
