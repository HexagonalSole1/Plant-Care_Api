package com.example.APISkeleton.web.controllers;

import com.example.APISkeleton.services.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rol")
public class RolController {

    private IUserRoleService iUserRoleService;

    @Autowired
    public RolController(IUserRoleService iUserRoleService) {
        this.iUserRoleService = iUserRoleService;
    }


}
