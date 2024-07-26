package com.example.APISkeleton.persistance.repositories;

import com.example.APISkeleton.persistance.entities.PlantRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlantRecordRepository extends CrudRepository<PlantRecord,Long> {
}
