package com.candelaconstruction.project.controller;

import com.candelaconstruction.project.model.Project;
import com.candelaconstruction.project.repository.ProjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects(
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "state", required = false) String state) {

        if (category != null && !category.equalsIgnoreCase("all")) {
            return ResponseEntity.ok(projectRepository.findByCat(category.toLowerCase()));
        }
        if (status != null) {
            return ResponseEntity.ok(projectRepository.findByStatus(status));
        }
        if (state != null) {
            return ResponseEntity.ok(projectRepository.findByStateIgnoreCase(state));
        }
        return ResponseEntity.ok(projectRepository.findAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<Project> getProjectBySlug(@PathVariable("slug") String slug) {
        return projectRepository.findBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
