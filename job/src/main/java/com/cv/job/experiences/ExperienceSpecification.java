package com.cv.job.experiences;

import com.cv.job.profile.Experience;
import org.springframework.data.jpa.domain.Specification;

public class ExperienceSpecification {

    public static Specification<Experience> withCandidateId(Long candidateId){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("candidate").get("id"), candidateId);
    }
}
