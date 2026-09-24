package com.candelaconstruction.hrnews.controller;

import com.candelaconstruction.hrnews.model.CorporateNews;
import com.candelaconstruction.hrnews.repository.CorporateNewsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "*")
public class NewsController {

    private final CorporateNewsRepository newsRepository;

    public NewsController(CorporateNewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping
    public ResponseEntity<List<CorporateNews>> getAllNews(
            @RequestParam(name = "category", required = false) String category) {
        if (category != null && !category.equalsIgnoreCase("all")) {
            return ResponseEntity.ok(newsRepository.findByCategoryIgnoreCase(category));
        }
        return ResponseEntity.ok(newsRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorporateNews> getNewsById(@PathVariable("id") String id) {
        return newsRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
