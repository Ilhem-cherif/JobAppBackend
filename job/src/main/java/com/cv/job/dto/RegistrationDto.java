package com.cv.job.dto;


import com.cv.job.models.UserRole;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RegistrationDto {
    @NotEmpty(message = "Firstname is mandatory")
    @NotBlank(message = "Firstname is mandatory")
    private String firstname;
    @NotEmpty(message = "Lastname is mandatory")
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotEmpty(message = "Password is mandatory")
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;
    @NotNull(message = "role cannot be null")
    private UserRole role;

    //specific to candidate
    private String personalResume;
    private String phoneNumber;
    private int age;
    private String photo;

    //specific to employer
    private String entrepriseName;
    private String entrepriseDescription;
    private String entrepriseLogo;
    private String entrepriseWebsite;
    private String entrepriseLocation;
}
