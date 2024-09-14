package com.cv.job.experiences;

import com.cv.job.profile.Experience;
import org.springframework.stereotype.Service;

@Service
public class ExperienceMapper {
    public Experience toExperience(ExperienceRequest request) {
        return Experience.builder()
                .id(request.id())
                .title(request.title())
                .description(request.description())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .build();
    }

    public ExperienceResponse toExperienceResponse(Experience experience) {
        return ExperienceResponse.builder()
                .id(experience.getId())
                .title(experience.getTitle())
                .description(experience.getDescription())
                .startDate(experience.getStartDate())
                .endDate(experience.getEndDate())
                .build();
    }
}
