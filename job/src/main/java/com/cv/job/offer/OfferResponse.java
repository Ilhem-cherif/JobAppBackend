package com.cv.job.offer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferResponse {

    private Integer id;
    private String title;
    private String description;
    private boolean isConfirmed;
    private String publisher;
    private String companyName;
    private String companyLocation;
    private String companyWebSite;
    private String companyDescription;

}

