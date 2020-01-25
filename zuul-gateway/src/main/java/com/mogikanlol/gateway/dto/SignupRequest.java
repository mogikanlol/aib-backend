package com.mogikanlol.gateway.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignupRequest {

    //TODO: Add complex validation
    @NotBlank
    private String username;

    //TODO: Add complex validation
    @NotBlank
    private String password;
}