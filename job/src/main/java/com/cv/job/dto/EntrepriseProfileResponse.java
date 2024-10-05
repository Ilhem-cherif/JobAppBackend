package com.cv.job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntrepriseProfileResponse {
    private Long id;
    private String entrepriseName;
    private String entrepriseDescription;
    private String entrepriseLogo;
    private String entrepriseWebsite;
    private String entrepriseLocation;
}
