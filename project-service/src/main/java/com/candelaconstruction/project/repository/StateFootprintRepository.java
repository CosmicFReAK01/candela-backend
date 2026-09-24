package com.candelaconstruction.project.repository;

import com.candelaconstruction.project.model.StateFootprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateFootprintRepository extends JpaRepository<StateFootprint, String> {
}
