package com.cv.job.educations;

import com.cv.job.profile.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EducationRepository extends JpaRepository<Education, Integer>, JpaSpecificationExecutor<Education> {
}
