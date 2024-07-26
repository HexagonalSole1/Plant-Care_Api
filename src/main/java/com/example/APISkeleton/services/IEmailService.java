package com.example.APISkeleton.services;

import com.example.APISkeleton.web.dtos.email.EmailRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;

public interface IEmailService {


    BaseResponse sendEmail(EmailRequest sendEmailRequest);
}
