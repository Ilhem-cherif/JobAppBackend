package com.cv.job.positions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionResponse {
    private Integer id;
    private String title;
    private String description;
    private String requirements;
    private String skills;
    private double salary;
    private String location;
    private Integer offerId;
}
