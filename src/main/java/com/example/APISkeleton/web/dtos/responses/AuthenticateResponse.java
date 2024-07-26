package com.example.APISkeleton.web.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateResponse {
    private String name;
    private String lastname;
    private String email;
    private String accessToken;
    private String refreshToken;
}