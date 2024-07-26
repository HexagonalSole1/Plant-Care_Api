package com.example.APISkeleton.web.dtos.email;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {

    private String to;
    private String subject;
    private String body;
    private String content;
}
