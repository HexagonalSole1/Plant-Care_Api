package com.example.APISkeleton.services.impls;

import com.example.APISkeleton.services.IEmailService;
import com.example.APISkeleton.web.dtos.email.EmailRequest;
import com.example.APISkeleton.web.dtos.responses.BaseResponse;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService {

    private final Resend resendClient;

    public EmailServiceImpl(Resend resendClient) {
        this.resendClient = resendClient;
    }

    @Override
    public BaseResponse sendEmail(EmailRequest request) {
        try {
            SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                    .from("deskrun@gmail.com")
                    .to(request.getTo())
                    .subject(request.getSubject())
                    .html(request.getContent())
                    .build();

            resendClient.emails().send(sendEmailRequest);

            return BaseResponse.builder()
                    .data(null)
                    .message("Email sent successfully")
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();

        } catch (ResendException e) {
            e.printStackTrace();
            return BaseResponse.builder()
                    .data(null)
                    .message("Failed to send email")
                    .success(false)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
