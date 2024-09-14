package com.cv.job.applications;

import org.springframework.data.jpa.domain.Specification;

public class ApplicationSpecification {

    public static Specification<Application> withCandidateId(Long candidateId){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("candidate").get("id"), candidateId);
    }
}
