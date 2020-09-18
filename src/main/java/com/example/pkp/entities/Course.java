package com.example.pkp.entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Train train;



    public Course(Train train, List<LocalDateTime> timeTable) {
        List<Stop> stops = train.getTrack().sortList();
        details = new HashMap<Stop, LocalDateTime>();
        for(int i =0;i<train.getTrack().getStops().size();i++) {

            this.details.put( stops.get(i), timeTable.get(i));
        }
    }

   @ElementCollection
   private Map<Stop, LocalDateTime> details;

    public Course() {

    }
}
