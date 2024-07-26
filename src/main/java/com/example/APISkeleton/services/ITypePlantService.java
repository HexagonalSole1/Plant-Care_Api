package com.example.APISkeleton.services;

import com.example.APISkeleton.web.dtos.category.request.CreateFamilyRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;

public interface ITypePlantService {
    BaseResponse create(CreateFamilyRequest request);
    BaseResponse get();

    BaseResponse delete(Long id);
}
