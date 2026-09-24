package com.candelaconstruction.project.repository;

import com.candelaconstruction.project.model.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceItem, String> {
    Optional<ServiceItem> findBySlug(String slug);
}
