package io.tool.full.stack.ppmtoolfullstack.services;

import io.tool.full.stack.ppmtoolfullstack.commons.ProjectStatus;
import io.tool.full.stack.ppmtoolfullstack.domain.Backlog;
import io.tool.full.stack.ppmtoolfullstack.domain.ProjectTask;
import io.tool.full.stack.ppmtoolfullstack.exceptions.ProjectNotFoundException;
import io.tool.full.stack.ppmtoolfullstack.repositories.BacklogRepository;
import io.tool.full.stack.ppmtoolfullstack.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

        try {
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

            if (backlog == null) {
                throw new ProjectNotFoundException("Project Not Found : " + projectIdentifier);
            }

            //set the bl to pt
            projectTask.setBacklog(backlog);

            //we want our project sequence to be like this: IDPRO-1  IDPRO-2  ...100 101
            Integer backlogPTSequence = backlog.getPTSequence();
            backlogPTSequence++;

            // set updated PTSequence to Backlog
            backlog.setPTSequence(backlogPTSequence);

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

        } catch (Exception e) {
            throw new ProjectNotFoundException("Project Not Found");
        }
    }

    public Iterable<ProjectTask> findBacklogById(String id) {
        List<ProjectTask> projectTasks = projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
        if (CollectionUtils.isEmpty(projectTasks)) {
            throw new ProjectNotFoundException("Project Not Found : " + id);
        }
        return projectTasks;
    }
}
