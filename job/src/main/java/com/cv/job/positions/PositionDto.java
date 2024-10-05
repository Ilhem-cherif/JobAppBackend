package com.cv.job.positions;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PositionDto(
        Integer id,
        @NotNull(message = "Title is mandatory")
        @NotEmpty(message = "Title is mandatory")
        String title,
        @NotNull(message = "Description is mandatory")
        @NotEmpty(message = "Description is mandatory")
        String description,
        @NotNull(message = "Requirement is mandatory")
        @NotEmpty(message = "Requirement is mandatory")
        String requirements,
        @NotNull(message = "Skill is mandatory")
        @NotEmpty(message = "Skill is mandatory")
        String skills,
        @NotNull(message = "Location is mandatory")
        @NotEmpty(message = "Location is mandatory")
        String Location,
        double salary,
        @NotNull(message = "offer should be selected")
        Integer offerId


) {
}
