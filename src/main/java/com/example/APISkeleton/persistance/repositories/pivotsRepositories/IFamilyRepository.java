package com.example.APISkeleton.persistance.repositories.pivotsRepositories;

import com.example.APISkeleton.persistance.entities.pivots.Category;
import com.example.APISkeleton.persistance.entities.pivots.Family;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IFamilyRepository extends CrudRepository<Family,Long> {
    Optional<Family> findByName(String name);

}
