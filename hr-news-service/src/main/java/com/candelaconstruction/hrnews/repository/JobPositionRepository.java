package com.candelaconstruction.hrnews.repository;

import com.candelaconstruction.hrnews.model.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, String> {
    List<JobPosition> findByDepartmentIgnoreCase(String department);
}
