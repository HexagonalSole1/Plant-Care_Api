package com.example.APISkeleton.web.controllers;


import com.example.APISkeleton.persistance.repositories.IPlantRecordRepository;
import com.example.APISkeleton.security.SocketIoService;
import com.example.APISkeleton.services.IPlantRecordService;
import com.example.APISkeleton.web.dtos.plants.request.CreatePlantRecordRequest;
import com.example.APISkeleton.web.dtos.plants.request.CreatePlantRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("plantRecord")
public class PlantRecordController{

    private IPlantRecordService service;


    @Autowired
    public PlantRecordController(IPlantRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createPlantRecord(@Validated @RequestBody CreatePlantRecordRequest request) {

        BaseResponse baseResponse = service.createPlantRecord(request);
        return baseResponse.buildResponseEntity();
    }
}
