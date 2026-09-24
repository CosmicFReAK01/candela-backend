package com.candelaconstruction.project;

import com.candelaconstruction.project.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ProjectServiceApplication.class)
class ProjectServiceApplicationTests {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void contextLoadsAndSeedsProjects() {
        assertTrue(projectRepository.count() > 0, "Projects should be seeded");
        assertTrue(projectRepository.findBySlug("jhbdpl-spie-capag").isPresent(), "JHBDPL project should exist");
    }
}
