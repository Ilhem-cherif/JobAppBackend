package com.cv.job.offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface JobOfferRepository extends JpaRepository<JobOffer, Integer>, JpaSpecificationExecutor<JobOffer> {

    @Query("""
            SELECT offer
            FROM JobOffer offer
            WHERE offer.isConfirmed = false
            """)
    Page<JobOffer> findAllDisplayableOffers(Pageable pageable);

    @Query("""
            SELECT j
            FROM JobOffer j
            WHERE (LOWER(j.title) LIKE LOWER(CONCAT('%', :query, '%'))
            OR LOWER(j.description) LIKE LOWER(CONCAT('%', :query, '%'))
            OR LOWER(j.publisher.entrepriseLocation) LIKE LOWER(CONCAT('%', :query, '%')))
            AND j.isConfirmed = false
            """)
    Page<JobOffer> searchOffers(String query,Pageable pageable);
}
