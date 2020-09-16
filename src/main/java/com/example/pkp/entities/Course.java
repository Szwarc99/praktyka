package com.example.pkp.entities;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Course {
    @Id
    private Long id;



    public Course(Track track, List<LocalDateTime> timeTable) {
        for(int i =0;i<track.getStops().size();i++) {
            track.sortList(track.getStops());
            this.details.put( track.sortList(track.getStops()).get(i), timeTable.get(i));
        }
    }
   @ElementCollection
   private Map<Stop, LocalDateTime> details;

    public Course() {

    }
}
