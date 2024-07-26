package com.example.APISkeleton.services.impls;


import com.example.APISkeleton.mappers.categoriesMapper.FamiliesMapper;
import com.example.APISkeleton.persistance.entities.pivots.Category;
import com.example.APISkeleton.persistance.entities.pivots.Family;
import com.example.APISkeleton.persistance.repositories.pivotsRepositories.IFamilyRepository;
import com.example.APISkeleton.services.IFamilyService;
import com.example.APISkeleton.web.dtos.category.request.CreateFamilyRequest;
import com.example.APISkeleton.web.dtos.category.response.CategoriesFoundResponse;
import com.example.APISkeleton.web.dtos.category.response.FamiliesFoundResponse;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import com.example.APISkeleton.web.exceptions.Plants.UniqueConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FamilyServiceImpl implements  IFamilyService {

    private final IFamilyRepository repository;
    private final FamiliesMapper mapper;

    @Autowired
    public FamilyServiceImpl(IFamilyRepository repository, FamiliesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BaseResponse get() {

        List<Family> FamilyList = (List<Family>) repository.findAll();

        List<FamiliesFoundResponse> response = FamilyList.stream()
                .map(family -> mapper.convertToResponse(family))
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(response)
                .message("Familys found")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse delete(Long id) {

        Optional<Family> optionalFamily = repository.findById(id);
        if (optionalFamily.isPresent()){
            repository.deleteById(id);
            return BaseResponse.builder()
                    .data(null)
                    .message("Family deleted successfully")
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();

        }
        UniqueConstraintViolationException uniqueConstraintViolationException = new UniqueConstraintViolationException("Family with id '" + id + "' not already exists.");
        return BaseResponse.builder()
                .data(uniqueConstraintViolationException.getCause())
                .message(uniqueConstraintViolationException.getMessage())
                .success(false)
                .httpStatus(uniqueConstraintViolationException.getHttpStatus())
                .build();
    }


    @Override
    public BaseResponse create(CreateFamilyRequest request) {

        Optional<Family> existingFamily = repository.findByName(request.getName());
        if (existingFamily.isPresent()) {
            UniqueConstraintViolationException uniqueConstraintViolationException = new UniqueConstraintViolationException("Family with name '" + request.getName() + "' already exists.");

            return BaseResponse.builder()
                    .data(uniqueConstraintViolationException.getCause())
                    .message(uniqueConstraintViolationException.getMessage())
                    .success(false)
                    .httpStatus(uniqueConstraintViolationException.getHttpStatus())
                    .build();
        }

        Family family = Family.builder()
                .name(request.getName())
                .build();
        repository.save(family);

        return BaseResponse.builder()
                .data(request.getName())
                .message("Family created successfully")
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }
}
