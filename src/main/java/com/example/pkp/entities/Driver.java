package com.example.pkp.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity @Getter
@Setter
@NoArgsConstructor
public class Driver {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotNull
    String firstname;
    @NotNull
    String secondname;

    public Driver(String firstname, String secondname) {
        this.setFirstname(firstname);
        this.setSecondname(secondname);
    }

}
