package com.example.APISkeleton.web.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.APISkeleton.services.IPlantService;
import com.example.APISkeleton.web.dtos.plants.request.CreatePlantRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

class PlantControllerTest {

    @Mock
    private IPlantService plantService;

    @InjectMocks
    private PlantController plantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

/*    @Test
    void createPlant() {
        // Arrange
        CreatePlantRequest request = new CreatePlantRequest();
        // Poblar el objeto request con los datos necesarios
        request.setCategories(Arrays.asList("Category1"));
        request.setTypes(Arrays.asList("Type1"));
        request.setFamilies(Arrays.asList("Family1"));
        request.setName("Fern");

        BaseResponse baseResponse = BaseResponse.builder()
                .data(null)
                .message("Plant added successfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();

        // Act
        ResponseEntity<BaseResponse> responseEntity = plantController.createPlant(request);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Plant added successfully", responseEntity.getBody().getMessage());

        verify(plantService).create(request);
    }*/
}
