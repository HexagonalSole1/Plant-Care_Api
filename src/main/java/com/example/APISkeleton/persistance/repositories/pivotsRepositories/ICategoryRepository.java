package com.example.APISkeleton.persistance.repositories.pivotsRepositories;

import com.example.APISkeleton.persistance.entities.pivots.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends CrudRepository<Category,Long> {
    Optional<Category> findByName(String name);

}
