package com.candelaconstruction.project.repository;

import com.candelaconstruction.project.model.EquipmentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentItem, String> {
}
