package com.example.APISkeleton.mappers.categoriesMapper;
import com.example.APISkeleton.persistance.entities.pivots.Family;
import com.example.APISkeleton.persistance.entities.pivots.TypePlant;
import com.example.APISkeleton.web.dtos.category.response.TypesFoundResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TypeMapper {
    public TypesFoundResponse convertToResponse(TypePlant typePlant) {
        TypesFoundResponse response = new TypesFoundResponse();
        response.setId(typePlant.getId());
        response.setName(typePlant.getName());

        return response;
    }

}
