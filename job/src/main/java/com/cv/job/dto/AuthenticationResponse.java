package com.cv.job.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private String status;
    private String token;
    private String expiredAt;
    private UserDto user;
    private String message;
}
