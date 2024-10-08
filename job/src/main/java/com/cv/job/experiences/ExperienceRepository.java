package com.cv.job.experiences;

import com.cv.job.profile.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExperienceRepository extends JpaRepository<Experience, Integer>, JpaSpecificationExecutor<Experience> {
}
