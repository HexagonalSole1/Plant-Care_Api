package com.example.APISkeleton.services;

import com.example.APISkeleton.web.dtos.plants.request.CreatePlantRecordRequest;
import com.example.APISkeleton.web.dtos.plants.request.CreatePlantRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;

public interface IPlantRecordService {
    BaseResponse createPlantRecord(CreatePlantRecordRequest request);

}
