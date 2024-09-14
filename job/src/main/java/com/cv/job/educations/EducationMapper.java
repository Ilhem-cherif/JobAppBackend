package com.cv.job.educations;

import com.cv.job.profile.Education;
import org.springframework.stereotype.Service;


@Service
public class EducationMapper {
    public Education toEducation(EducationRequest request) {
        return Education.builder()
                .id(request.id())
                .title(request.title())
                .description(request.description())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .build();
    }

    public EducationResponse toEducationResponse(Education education) {
        return EducationResponse.builder()
                .id(education.getId())
                .title(education.getTitle())
                .description(education.getDescription())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .build();
    }
}
