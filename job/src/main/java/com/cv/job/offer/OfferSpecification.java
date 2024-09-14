package com.cv.job.offer;

import org.springframework.data.jpa.domain.Specification;

public class OfferSpecification {

    public static Specification<JobOffer> withPublisherId(Long publisherId){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("publisher").get("id"), publisherId);
    }

}
