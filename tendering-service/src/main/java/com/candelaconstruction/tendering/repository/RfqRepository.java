package com.candelaconstruction.tendering.repository;

import com.candelaconstruction.tendering.model.RfqEnquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RfqRepository extends JpaRepository<RfqEnquiry, Long> {
    Optional<RfqEnquiry> findByReferenceNo(String referenceNo);
}
