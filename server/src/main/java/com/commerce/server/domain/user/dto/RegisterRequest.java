package com.commerce.server.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Firstname is required")
    @Size(min = 3,max = 50,message = "Firstname must be between 3 and 50 characters")
    private String firstName;

    @NotBlank(message = "Lastname is required")
    @Size(min = 3,max = 50,message = "Lastname must be between 3 and 50 characters")
    private String lastName;

    @NotBlank(message = "Username is required")
    @Size(min = 3,max = 50,message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Password must be required")
    @Size(min = 8,message = "Password length must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$"
            ,message = "The password must contain at least one uppercase letter, one lowercase letter, one number, and one special character (@#$%^&+=!)"
    )
    private String password;
}
