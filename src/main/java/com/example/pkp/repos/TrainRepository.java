package com.example.pkp.repos;

import com.example.pkp.entities.Train;
import org.springframework.data.repository.CrudRepository;

public interface TrainRepository extends CrudRepository <Train,Long> {
    Train findByName(String name);
}
