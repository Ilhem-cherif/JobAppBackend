package com.cv.job.applications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationResponse {
    private Integer id;
    private String status;
    private String submittedDate;
    private byte[] cv;
    private String cvName;
    private String positionName;
    private Long candidateId;
}
