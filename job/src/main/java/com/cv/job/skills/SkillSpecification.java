package com.cv.job.skills;

import com.cv.job.profile.Skill;
import org.springframework.data.jpa.domain.Specification;

public class SkillSpecification {

    public static Specification<Skill> withCandidateId(Long candidateId){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("candidate").get("id"), candidateId);
    }
}
