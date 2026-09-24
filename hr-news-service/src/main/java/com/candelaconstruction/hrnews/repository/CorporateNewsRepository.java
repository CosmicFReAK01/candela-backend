package com.candelaconstruction.hrnews.repository;

import com.candelaconstruction.hrnews.model.CorporateNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorporateNewsRepository extends JpaRepository<CorporateNews, String> {
    List<CorporateNews> findByCategoryIgnoreCase(String category);
}
