package com.example.pkp.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity @Getter @Setter @NoArgsConstructor
public class Train implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    public Train(String name) {
        this.setName(name);
    }
}
