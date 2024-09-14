package com.cv.job.skills;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SkillRequest(
        Integer id,
        @NotNull(message = "300")
        @NotEmpty(message = "300")
        String title,
        String description,
        String level
) {
}
