package io.tool.full.stack.ppmtoolfullstack.web;

import io.tool.full.stack.ppmtoolfullstack.domain.Project;
import io.tool.full.stack.ppmtoolfullstack.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author badrikant.soni on 22/01/19
 */
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<Project> createNewProject(@RequestBody Project project) {
        Project newProject = projectService.saveOrUpdate(project);
        return new ResponseEntity<Project>(newProject, HttpStatus.CREATED);
    }
}
