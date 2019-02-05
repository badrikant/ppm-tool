package io.tool.full.stack.ppmtoolfullstack.services;

import io.tool.full.stack.ppmtoolfullstack.commons.ProjectStatus;
import io.tool.full.stack.ppmtoolfullstack.domain.Backlog;
import io.tool.full.stack.ppmtoolfullstack.domain.ProjectTask;
import io.tool.full.stack.ppmtoolfullstack.exceptions.ProjectIdException;
import io.tool.full.stack.ppmtoolfullstack.repositories.BacklogRepository;
import io.tool.full.stack.ppmtoolfullstack.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author badrikant.soni on 05/02/19
 */

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        //PTs to be added to a specific project, project != null, BL exists
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        if (backlog == null) {
            throw new ProjectIdException("Project Not Found");
        }

        //set the bl to pt
        projectTask.setBacklog(backlog);

        //we want our project sequence to be like this: IDPRO-1  IDPRO-2  ...100 101
        Integer backlogPTSequence = backlog.getPTSequence();
        backlogPTSequence++;
        projectTask.setProjectSequence(projectIdentifier + "-" + backlogPTSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        // set initial status when status is null or empty
        if (projectTask.getStatus() == null || projectTask.getStatus() == "") {
            projectTask.setStatus(ProjectStatus.TO_DO.name());
        }

        // set initial priority when priority is null or 0
        if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
            projectTask.setPriority(3);
        }
        ProjectTask projectTask1 = projectTaskRepository.save(projectTask);
        return projectTask1;
    }
}
