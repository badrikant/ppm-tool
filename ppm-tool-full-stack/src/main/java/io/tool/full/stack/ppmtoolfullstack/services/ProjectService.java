package io.tool.full.stack.ppmtoolfullstack.services;

import io.tool.full.stack.ppmtoolfullstack.domain.Backlog;
import io.tool.full.stack.ppmtoolfullstack.domain.Project;
import io.tool.full.stack.ppmtoolfullstack.domain.User;
import io.tool.full.stack.ppmtoolfullstack.exceptions.ProjectIdException;
import io.tool.full.stack.ppmtoolfullstack.exceptions.ProjectNotFoundException;
import io.tool.full.stack.ppmtoolfullstack.repositories.BacklogRepository;
import io.tool.full.stack.ppmtoolfullstack.repositories.ProjectRepository;
import io.tool.full.stack.ppmtoolfullstack.repositories.UserRepository;
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

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;

    public Project saveOrUpdate(Project project, String username) {

        // only correct project owner should be able to update the project.
        if (project.getId() != null) {
            Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
            if (existingProject != null && (!existingProject.getProjectLeader().equals(username))) {
                throw new ProjectNotFoundException("project not found in your project");
            } else if (existingProject == null) {
                throw new ProjectNotFoundException("Project with ID: '" + project.getProjectIdentifier() + "' cannot be updated because it doesn't exist");
            }
        }

        try {
            User user = userRepository.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            // set the relationship between Project and Backlog Entity
            if (project.getId() == null) {
                // whenever project object is created, lets create a new Backlog object
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }
            if (project.getId() != null) {
                // in update case, find existing backlog and set it to project
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId, String username) {
        Project projectIdentifier = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (projectIdentifier == null) {
            throw new ProjectIdException("Project ID '" + projectId + "' does't exist");
        }
        // throw an error if project id does't belong to correct owner
        if (!projectIdentifier.getProjectLeader().equals(username)) {
            throw new ProjectNotFoundException("Project Not Found in your account");
        }
        return projectIdentifier;
    }

    public Iterable<Project> findAllProjects(String username) {
        return projectRepository.findAllByProjectLeader(username);
    }

    public void deleteProjectByProjectIdentifier(String projectId, String username) {

        // allow to delete projects with correct owners.
        projectRepository.delete(findProjectByIdentifier(projectId, username));
    }
}
