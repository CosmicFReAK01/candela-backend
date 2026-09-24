package com.candelaconstruction.hrnews.repository;

import com.candelaconstruction.hrnews.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    Optional<JobApplication> findByApplicationRef(String applicationRef);
}
