package com.cv.job.skills;

import com.cv.job.profile.Skill;
import org.springframework.stereotype.Service;

@Service
public class SkillMapper {
    public Skill toskill(SkillRequest request) {

        return Skill.builder()
                .id(request.id())
                .title(request.title())
                .description(request.description())
                .level(request.level())
                .build();
    }

    public SkillResponse toSkillResponse(Skill skill) {
        return SkillResponse.builder()
                .id(skill.getId())
                .title(skill.getTitle())
                .description(skill.getDescription())
                .level(skill.getLevel())
                .build();
    }
}
