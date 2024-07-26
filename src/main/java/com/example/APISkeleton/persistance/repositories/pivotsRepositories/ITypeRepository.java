package com.example.APISkeleton.persistance.repositories.pivotsRepositories;

import com.example.APISkeleton.persistance.entities.pivots.Category;
import com.example.APISkeleton.persistance.entities.pivots.TypePlant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface ITypeRepository extends CrudRepository<TypePlant,Long> {
    Optional<TypePlant> findByName(String name);

}
