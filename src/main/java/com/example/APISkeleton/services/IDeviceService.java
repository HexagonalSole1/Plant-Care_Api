package com.example.APISkeleton.services;

import com.example.APISkeleton.web.dtos.devices.request.CreateDeviceRequest;
import com.example.APISkeleton.web.dtos.devices.request.GetDeviceByMacRequest;
import com.example.APISkeleton.web.dtos.plants.request.CreatePlantRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;

public interface IDeviceService {
    BaseResponse createDevice(CreateDeviceRequest request);
    BaseResponse getDevice();

    BaseResponse getDeviceByMAC(String macRequest);
    BaseResponse getDeviceByEmail(String emailRequest);


    BaseResponse deleteDeviceByMAC(String macRequest);


}
