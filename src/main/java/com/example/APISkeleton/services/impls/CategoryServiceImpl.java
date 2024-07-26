package com.example.APISkeleton.services.impls;

import com.example.APISkeleton.mappers.categoriesMapper.CategoriesMapper;
import com.example.APISkeleton.persistance.entities.pivots.Category;
import com.example.APISkeleton.persistance.repositories.pivotsRepositories.ICategoryRepository;
import com.example.APISkeleton.services.ICategoryService;
import com.example.APISkeleton.web.dtos.category.request.CreateCategoryRequest;
import com.example.APISkeleton.web.dtos.category.response.CategoriesFoundResponse;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import com.example.APISkeleton.web.exceptions.Plants.UniqueConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private final CategoriesMapper mapper;

    @Autowired
    public CategoryServiceImpl(ICategoryRepository categoryRepository, CategoriesMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;

    }

    @Override
    public BaseResponse create(CreateCategoryRequest request) {

        Optional<Category> existingCategory = categoryRepository.findByName(request.getName());
        if (existingCategory.isPresent()) {
            UniqueConstraintViolationException uniqueConstraintViolationException = new UniqueConstraintViolationException("Category with name '" + request.getName() + "' already exists.");

            return BaseResponse.builder()
                    .data(uniqueConstraintViolationException.getCause())
                    .message(uniqueConstraintViolationException.getMessage())
                    .success(false)
                    .httpStatus(uniqueConstraintViolationException.getHttpStatus())
                    .build();
        }

        Category category = Category.builder()
                .name(request.getName())
                .build();
        categoryRepository.save(category);

        return BaseResponse.builder()
                .data(category)
                .message("Category created successfully")
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse get() {

        List<Category> categoryList = (List<Category>) categoryRepository.findAll();

        List<CategoriesFoundResponse> response = categoryList.stream()
                .map(category -> mapper.convertToResponse(category))
                .collect(Collectors.toList());

            return BaseResponse.builder()
                    .data(response)
                    .message("Categories found")
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();
    }

    @Override
    public BaseResponse delete(Long id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            categoryRepository.deleteById(id);
            return BaseResponse.builder()
                    .data(null)
                    .message("Category deleted successfully")
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();

        }
        UniqueConstraintViolationException uniqueConstraintViolationException = new UniqueConstraintViolationException("Category with id '" + id + "' not already exists.");
        return BaseResponse.builder()
                .data(uniqueConstraintViolationException.getCause())
                .message(uniqueConstraintViolationException.getMessage())
                .success(false)
                .httpStatus(uniqueConstraintViolationException.getHttpStatus())
                .build();
    }
}
