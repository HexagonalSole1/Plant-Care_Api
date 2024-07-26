package com.example.APISkeleton.web.controllers;

import com.example.APISkeleton.persistance.entities.Plant;
import com.example.APISkeleton.services.IPlantService;
import com.example.APISkeleton.web.dtos.plants.request.CreatePlantRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("plant")
public class PlantController {

    private final IPlantService PlantService;

    @Autowired
    public PlantController(IPlantService iPlantService) {
        this.PlantService = iPlantService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createPlant(@Valid @RequestBody CreatePlantRequest request) {
        BaseResponse baseResponse = PlantService.create(request);
        return baseResponse.buildResponseEntity();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deletePlantById(@PathVariable Long id) {
        BaseResponse baseResponse = PlantService.deletePlantById(id);

        Plant plant = Plant.builder().build();
        return baseResponse.buildResponseEntity();
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updatePlant(@PathVariable Long id,@Validated @RequestBody CreatePlantRequest request) {
        BaseResponse baseResponse = PlantService.update(id,request);

        return baseResponse.buildResponseEntity();
    }
    @GetMapping
    public ResponseEntity<BaseResponse> getPlant() {
        BaseResponse baseResponse = PlantService.getPlant();

        return baseResponse.buildResponseEntity();
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getPlantById(@PathVariable Long id) {
        BaseResponse baseResponse = PlantService.getById(id);


        return baseResponse.buildResponseEntity();
    }

}
