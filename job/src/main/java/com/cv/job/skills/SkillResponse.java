package com.cv.job.skills;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillResponse {
    private Integer id;
    private String title;
    private String description;
    private String level;
}
