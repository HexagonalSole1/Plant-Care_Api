package com.example.APISkeleton.mappers.categoriesMapper;

import com.example.APISkeleton.persistance.entities.Plant;
import com.example.APISkeleton.persistance.entities.pivots.Category;
import com.example.APISkeleton.persistance.entities.pivots.Family;
import com.example.APISkeleton.persistance.entities.pivots.TypePlant;
import com.example.APISkeleton.web.dtos.category.response.FamiliesFoundResponse;
import com.example.APISkeleton.web.dtos.plants.response.PlantResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Component
public class FamiliesMapper {

    public FamiliesFoundResponse convertToResponse(Family family) {
        FamiliesFoundResponse response = new FamiliesFoundResponse();
        response.setId(family.getId());
        response.setName(family.getName());

        return response;
    }




}
