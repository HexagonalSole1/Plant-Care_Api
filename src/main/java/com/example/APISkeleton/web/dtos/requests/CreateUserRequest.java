package com.example.APISkeleton.web.dtos.requests;

import com.example.APISkeleton.web.validators.ValidGender;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateUserRequest {

    @NotBlank(message = "name is required")
    private String name;

    @JsonProperty("last_name")
    @NotBlank(message = "last_name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "gender is required")
    @ValidGender(message = "Gender must be 'H' or 'M'")
    private Character gender;
}
