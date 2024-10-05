package com.cv.job.educations;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public record EducationRequest(
        Integer id,
        @NotNull(message = "Title is mandatory")
        @NotEmpty(message = "Title is mandatory")
        String title,
        @NotNull(message = "Description is mandatory")
        @NotEmpty(message = "Description is mandatory")
        String description,
        @NotNull(message = "Start date is mandatory")
        @PastOrPresent(message = "Start date is not valid")
        Date startDate,
        @NotNull(message = "End date is mandatory")
        Date endDate
) {
}
