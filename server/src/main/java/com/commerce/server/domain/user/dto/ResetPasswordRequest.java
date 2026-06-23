package com.commerce.server.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordRequest {
    @NotBlank(message = "Token is required")
    private String token;

    @NotBlank(message = "Password must be required")
    @Size(min = 8,message = "Password length must be at least 8 characters")
    private String password;

    @NotBlank(message = "Confirm password must be required")
    @Size(min = 8,message = "Confirm password length must be at least 8 characters")
    private String confirmPassword;
}
