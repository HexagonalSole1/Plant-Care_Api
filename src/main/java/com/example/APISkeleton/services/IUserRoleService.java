package com.example.APISkeleton.services;

import com.example.APISkeleton.web.dtos.requests.CreateRoleRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import com.example.APISkeleton.web.dtos.responses.GetRoleResponse;

import java.util.List;

public interface IUserRoleService {
    List<GetRoleResponse> getRolesByUserId(Long userId);

}
