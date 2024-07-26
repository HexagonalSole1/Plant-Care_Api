package com.example.APISkeleton.web.controllers;


import com.example.APISkeleton.services.IDeviceService;
import com.example.APISkeleton.web.dtos.devices.request.CreateDeviceRequest;
import com.example.APISkeleton.web.dtos.devices.request.GetDeviceByMacRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("device")
public class DeviceController {

    IDeviceService deviceService;

    @Autowired
    public DeviceController(IDeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createDevice(@Valid @RequestBody CreateDeviceRequest request) {
        BaseResponse baseResponse = deviceService.createDevice(request);
        return baseResponse.buildResponseEntity();
    }
    @GetMapping("/mac")
    public ResponseEntity<BaseResponse> getDeviceByMac(@RequestParam String macAddress) {
        BaseResponse baseResponse = deviceService.getDeviceByMAC(macAddress);
        return baseResponse.buildResponseEntity();
    }
    @GetMapping("/email")
    public ResponseEntity<BaseResponse> getDeviceByUser(@RequestParam String email) {
        BaseResponse baseResponse = deviceService.getDeviceByEmail(email);
        return baseResponse.buildResponseEntity();
    }
    @DeleteMapping("/mac")
    public ResponseEntity<BaseResponse> deleteDeviceByMac(@RequestParam String macAddress) {
        BaseResponse baseResponse = deviceService.deleteDeviceByMAC(macAddress);
        return baseResponse.buildResponseEntity();
    }


    @GetMapping
    public ResponseEntity<BaseResponse> getDevice() {
        BaseResponse baseResponse = deviceService.getDevice();
        return baseResponse.buildResponseEntity();
    }
}
