package com.cv.job.applications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer>, JpaSpecificationExecutor<Application> {
    @Query("""
            SELECT application
            FROM Application application
            WHERE application.position.id = :positionId
            """)
    List<Application> findAllByPositionId(Integer positionId);
}
