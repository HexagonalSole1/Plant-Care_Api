package com.example.APISkeleton.persistance.repositories;

import com.example.APISkeleton.persistance.entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IPlantRepository extends JpaRepository<Plant, Long> {
    Optional<Plant> findByName(String name);

}
