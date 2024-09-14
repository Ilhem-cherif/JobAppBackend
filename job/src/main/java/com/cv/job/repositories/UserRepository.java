package com.cv.job.repositories;


import com.cv.job.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    //Spring Data Patterns
    Optional<ApplicationUser> findByEmail(String email);
}
