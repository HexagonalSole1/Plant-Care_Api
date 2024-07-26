package com.example.APISkeleton.web.controllers;

import com.example.APISkeleton.services.IEmailService;
import com.example.APISkeleton.web.dtos.email.EmailRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("emails")
public class EmailController {

    private final IEmailService emailService;

    @Autowired
    public EmailController(IEmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<BaseResponse> sendEmail(@RequestBody EmailRequest emailRequest) {
        BaseResponse response = emailService.sendEmail(emailRequest);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
