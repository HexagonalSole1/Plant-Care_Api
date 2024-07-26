package com.example.APISkeleton.mappers.categoriesMapper;

import com.example.APISkeleton.persistance.entities.pivots.Category;
import com.example.APISkeleton.web.dtos.category.response.CategoriesFoundResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CategoriesMapper {

    public CategoriesFoundResponse convertToResponse(Category category) {
        CategoriesFoundResponse response = new CategoriesFoundResponse();
        response.setId(category.getId());
        response.setName(category.getName());

        return response;
    }


}
