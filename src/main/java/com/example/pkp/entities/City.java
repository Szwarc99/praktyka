package com.example.pkp.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.*;

@Entity
@Data @Getter @Setter @NoArgsConstructor
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    public City(String name) {
        this.name = name;
    }
}
