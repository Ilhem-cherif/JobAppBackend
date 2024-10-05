package com.cv.job.offer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record JobOfferRequest(
        Integer id,
        @NotNull(message = "Title is mandatory")
        @NotEmpty(message = "Title is mandatory")
        String title,
        @NotNull(message = "Description is mandatory")
        @NotEmpty(message = "Description is mandatory")
        String description,
        boolean isConfirmed
) {
}
