package com.example.pkp.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@Entity @Getter
@Setter
@NoArgsConstructor
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Train train;

    @NotNull
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Route route;

    @NotNull
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Driver driver;


   @NotNull
   @ElementCollection
   private Map<City, LocalDateTime> details;

   public Course(Driver driver, Train train, Route route, List<LocalDateTime> timeTable) {
       this.setDriver(driver);
       this.setRoute(route);
       this.setTrain(train);
       Map<Integer,City> cities =this.getRoute().getCities();
       for(int i =0;i<cities.size();i++) {
           this.details.put(cities.get(i), timeTable.get(i));
       }



   }

   /*public Course(Driver byDriverName, Optional<Train> byId, Optional<Route> byId1, List<LocalDateTime> listOfDates) {
        this.setDriver(driver);
        this.setRoute(route);
        this.setTrain(train);
        Map<Integer,City> cities =this.getRoute().getCities();
        for(int i =0;i<cities.size();i++) {
            this.details.put(cities.get(i), listOfDates.get(i));
        }
    }*/
}
