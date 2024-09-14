package com.cv.job.educations;

import com.cv.job.profile.Education;
import org.springframework.data.jpa.domain.Specification;

public class EducationSpecification {

    public static Specification<Education> withCandidateId(Long candidateId){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("candidate").get("id"), candidateId);
    }
}
