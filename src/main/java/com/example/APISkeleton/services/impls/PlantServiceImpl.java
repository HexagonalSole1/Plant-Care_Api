package com.example.APISkeleton.services.impls;

import com.example.APISkeleton.mappers.plantMapper.PlantMapper;
import com.example.APISkeleton.persistance.entities.Plant;
import com.example.APISkeleton.persistance.repositories.IPlantRepository;
import com.example.APISkeleton.services.IPlantService;
import com.example.APISkeleton.web.dtos.plants.request.CreatePlantRequest;
import com.example.APISkeleton.web.dtos.plants.response.PlantResponse;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantServiceImpl implements IPlantService {
    private  final PlantMapper mapper;
    private final IPlantRepository plantRepository;


    @Autowired
    public PlantServiceImpl(PlantMapper mapper, IPlantRepository plantRepository) {
        this.mapper = mapper;
        this.plantRepository = plantRepository;

    }


    @Override
    public BaseResponse getById(Long id) {
        Optional<Plant> optionalPlant = plantRepository.findById(id);

        if (optionalPlant.isPresent()){
            PlantResponse response = mapper.PlantToPlantResponse(optionalPlant.get());
            return BaseResponse.builder()
                    .data(response)
                    .message("Plant found")
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        return BaseResponse.builder()
                .data(null)
                .message("Plant Not found")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse update(Long id, CreatePlantRequest request) {
        Optional<Plant> optionalPlant = plantRepository.findById(id);
        if (optionalPlant.isPresent()) {
            Plant plant = optionalPlant.get();
            plant= mapper.convertUpdatePlantRequestToPlant(request,request.getCategories(),request.getTypes(),request.getFamilies(),plant);
            plantRepository.save(plant);
            return BaseResponse.builder()
                    .data(null)
                    .message("Plant updated")
                    .success(false)
                    .httpStatus(HttpStatus.OK)
                    .build();

        } else {
            return BaseResponse.builder()
                    .data(null)
                    .message("Plant not found")
                    .success(false)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
            }
    }

    @Override
    public BaseResponse deletePlantById(Long id) {
        Optional<Plant> optionalPlant = plantRepository.findById(id);
      if (optionalPlant.isPresent()){
          PlantResponse response = mapper.PlantToPlantResponse(optionalPlant.get());
          plantRepository.delete(optionalPlant.get());

          return BaseResponse.builder()
                  .data(response)
                  .message("Plant eliminated")
                  .success(true)
                  .httpStatus(HttpStatus.OK)
                  .build();
      }
        return BaseResponse.builder()
                .data(null)
                .message("Plant Not found")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }



    @Override
    public BaseResponse getPlant() {

        List<Plant> plants = plantRepository.findAll();
        List<PlantResponse> response = mapper.toPlantResponseDTOs(plants);
        return BaseResponse.builder()
                .data(response)
                .message("Plants found")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();

    }

    @Override
    public BaseResponse create(CreatePlantRequest request) {

        Plant plant = mapper.convertCreatePlantRequestToPlant(request, request.getCategories(), request.getTypes(), request.getFamilies());
        plantRepository.save(plant);

        return BaseResponse.builder()
                .data(null)
                .message("Plant added successfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

}

