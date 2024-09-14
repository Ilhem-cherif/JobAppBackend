package com.cv.job.experiences;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExperienceResponse {

    private Integer id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
}
