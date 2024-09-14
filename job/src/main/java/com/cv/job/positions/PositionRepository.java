package com.cv.job.positions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Integer> {

    @Query("""
            SELECT position
            FROM Position position
            WHERE position.offer.id = :offerId
            """)
    List<Position> findAllByOfferId(Integer offerId);
}

