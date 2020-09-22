package com.example.pkp.entities;

import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Getter @Setter @NoArgsConstructor
public class Route implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;

    @NotNull
    @ManyToMany(cascade = {CascadeType.ALL})
    private Map<Integer,City> cities;

    public Route(String name,Map<Integer,City> cities) {
        this.setName(name);
        this.setCities(cities);
    }


}
