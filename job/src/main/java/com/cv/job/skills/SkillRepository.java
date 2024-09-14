package com.cv.job.skills;

import com.cv.job.profile.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SkillRepository extends JpaRepository<Skill, Integer>, JpaSpecificationExecutor<Skill> {
}
