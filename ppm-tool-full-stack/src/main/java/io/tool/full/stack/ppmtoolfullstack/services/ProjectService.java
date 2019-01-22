package io.tool.full.stack.ppmtoolfullstack.services;

import io.tool.full.stack.ppmtoolfullstack.domain.Project;
import io.tool.full.stack.ppmtoolfullstack.exceptions.ProjectIdException;
import io.tool.full.stack.ppmtoolfullstack.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author badrikant.soni on 22/01/19
 * Q.Why do we need service layer ?
 * You always want to have your logic abstracted from the controller as much as you can obviously.
 * And then you only pretty much just want the controller to be a router rather than a place that holds
 * your logic. So this is why I want to always use a service layer that talks to the repository rather than having
 * a bunch of logic on the controller layer.
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project projectIdentifier = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (projectIdentifier == null) {
            throw new ProjectIdException("Project ID '" + projectId + "' does't exist");
        }
        return projectIdentifier;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }
}
