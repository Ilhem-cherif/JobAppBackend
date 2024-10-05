package com.cv.job.skills;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SkillRequest(
        Integer id,
        @NotNull(message = "Title is mandatory")
        @NotEmpty(message = "Title is mandatory")
        String title,
        String description,
        String level
) {
}
