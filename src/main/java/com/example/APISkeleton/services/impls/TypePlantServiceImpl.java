package com.example.APISkeleton.services.impls;


import com.example.APISkeleton.mappers.categoriesMapper.TypeMapper;
import com.example.APISkeleton.persistance.entities.pivots.Category;
import com.example.APISkeleton.persistance.entities.pivots.TypePlant;
import com.example.APISkeleton.persistance.repositories.pivotsRepositories.ITypeRepository;
import com.example.APISkeleton.services.ITypePlantService;
import com.example.APISkeleton.web.dtos.category.request.CreateFamilyRequest;
import com.example.APISkeleton.web.dtos.category.response.CategoriesFoundResponse;
import com.example.APISkeleton.web.dtos.category.response.TypesFoundResponse;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import com.example.APISkeleton.web.exceptions.Plants.UniqueConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypePlantServiceImpl implements ITypePlantService {

    private final ITypeRepository repository;
    private final TypeMapper mapper;

    @Autowired
    public TypePlantServiceImpl(ITypeRepository repository, TypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BaseResponse create(CreateFamilyRequest request) {

        Optional<TypePlant> existingTypePlant = repository.findByName(request.getName());
        if (existingTypePlant.isPresent()) {
            UniqueConstraintViolationException uniqueConstraintViolationException = new UniqueConstraintViolationException("Type of plant with name '" + request.getName() + "' already exists.");

            return BaseResponse.builder()
                    .data(uniqueConstraintViolationException.getCause())
                    .message(uniqueConstraintViolationException.getMessage())
                    .success(false)
                    .httpStatus(uniqueConstraintViolationException.getHttpStatus())
                    .build();
        }

        TypePlant typePlant = TypePlant.builder()
                .name(request.getName())
                .build();
        repository.save(typePlant);

        return BaseResponse.builder()
                .data(request.getName())
                .message("Type of Plant created successfully")
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse get() {

        List<TypePlant> typePlants = (List<TypePlant>) repository.findAll();

        List<TypesFoundResponse> response = typePlants.stream()
                .map(mapper::convertToResponse)
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

        Optional<TypePlant> optionalTypePlant = repository.findById(id);
        if (optionalTypePlant.isPresent()){
            repository.deleteById(id);
            return BaseResponse.builder()
                    .data(null)
                    .message("Types deleted successfully")
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();

        }
        UniqueConstraintViolationException uniqueConstraintViolationException = new UniqueConstraintViolationException("Types with id '" + id + "' not already exists.");
        return BaseResponse.builder()
                .data(uniqueConstraintViolationException.getCause())
                .message(uniqueConstraintViolationException.getMessage())
                .success(false)
                .httpStatus(uniqueConstraintViolationException.getHttpStatus())
                .build();
    }

}
