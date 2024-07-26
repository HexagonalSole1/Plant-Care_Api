package com.example.APISkeleton.web.controllers;

import com.example.APISkeleton.services.IUserService;
import com.example.APISkeleton.web.dtos.requests.CreateUserRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
    public class UserController {
        private final IUserService userService;

        public UserController(IUserService userService) {
            this.userService = userService;
        }

        @PostMapping
        public ResponseEntity<BaseResponse> create(@Valid @RequestBody CreateUserRequest request) {
            BaseResponse baseResponse = userService.create(request);

            return baseResponse.buildResponseEntity();
        }

    @GetMapping
    public ResponseEntity<BaseResponse> getUsers() {
        BaseResponse baseResponse = userService.getUsers();

        return baseResponse.buildResponseEntity();
    }
    @GetMapping("/by-email/{email}")
    public ResponseEntity<BaseResponse> getUserById(@PathVariable String email) {
        System.out.println(email);
        BaseResponse baseResponse = userService.getUserByEmailAdrian(email);

        return baseResponse.buildResponseEntity();
    }

}
