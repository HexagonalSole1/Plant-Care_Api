package com.example.APISkeleton.web.controllers;

import com.example.APISkeleton.services.IStatiticsService;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

    private IStatiticsService service;

    @Autowired
    public StatisticsController(IStatiticsService iStatiticsService) {
        this.service = iStatiticsService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getCreateAtUsers(){
        BaseResponse baseResponse = service.getCreateAtUsers();

        return baseResponse.buildResponseEntity();
    }
}
