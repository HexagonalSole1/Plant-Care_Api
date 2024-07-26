package com.example.APISkeleton.services;


import com.example.APISkeleton.web.dtos.plants.request.CreatePlantRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;

public interface IPlantService {
    BaseResponse create(CreatePlantRequest request);
    BaseResponse getById(Long id);


    BaseResponse update(Long id, CreatePlantRequest request);

    BaseResponse deletePlantById(Long id);


    BaseResponse getPlant();
}
