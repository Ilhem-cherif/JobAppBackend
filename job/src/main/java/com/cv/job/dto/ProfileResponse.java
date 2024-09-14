package com.cv.job.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponse {

    private String firstName;
    private String lastName;
    private String personalResume;
    private String phoneNumber;
    private String email;
    private int age;

}
