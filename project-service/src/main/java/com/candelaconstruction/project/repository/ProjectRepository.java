package com.candelaconstruction.project.repository;

import com.candelaconstruction.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
    Optional<Project> findBySlug(String slug);
    List<Project> findByCat(String cat);
    List<Project> findByStatus(String status);
    List<Project> findByStateIgnoreCase(String state);
}
