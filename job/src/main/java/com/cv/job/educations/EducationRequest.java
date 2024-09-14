package com.cv.job.educations;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public record EducationRequest(
        Integer id,
        @NotNull(message = "400")
        @NotEmpty(message = "400")
        String title,
        @NotNull(message = "401")
        @NotEmpty(message = "401")
        String description,
        @NotNull(message = "402")
        @PastOrPresent(message = "402")
        Date startDate,
        @NotNull(message = "403")
        Date endDate
) {
}
