package com.cv.job.positions;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PositionDto(
        Integer id,
        @NotNull(message = "200")
        @NotEmpty(message = "200")
        String title,
        @NotNull(message = "201")
        @NotEmpty(message = "201")
        String description,
        @NotNull(message = "202")
        @NotEmpty(message = "202")
        String requirements,
        @NotNull(message = "203")
        @NotEmpty(message = "203")
        String skills,
        @NotNull(message = "204")
        @NotEmpty(message = "204")
        String Location,
        double salary,
        @NotNull(message = "205")
        Integer offerId


) {
}
