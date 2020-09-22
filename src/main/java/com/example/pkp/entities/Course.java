package com.example.pkp.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@Entity @Getter
@Setter
@NoArgsConstructor
public class Course implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Train train;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Route route;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Driver driver;


   @NotNull
   @ElementCollection
   private Map<City, LocalDateTime> details = new HashMap<City, LocalDateTime>();

   public Course(Driver driver, Train train, Route route, List<LocalDateTime> timeTable) {
       setDriver(driver);
       setRoute(route);
       setTrain(train);
       Map<Integer,City> cities =getRoute().getCities();
       for(int i =0;i<cities.size();i++) {
           details.put(cities.get(i), timeTable.get(i));
       }
   }


}
