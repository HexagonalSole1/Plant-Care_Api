package com.example.APISkeleton.services;

import com.example.APISkeleton.web.dtos.category.request.CreateCategoryRequest;
import com.example.APISkeleton.web.dtos.requests.CreateUserRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;

public interface ICategoryService {
    BaseResponse create(CreateCategoryRequest request);

    BaseResponse get();

    BaseResponse delete(Long id);
}
