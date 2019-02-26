package io.tool.full.stack.ppmtoolfullstack.services;

import io.tool.full.stack.ppmtoolfullstack.commons.ProjectStatus;
import io.tool.full.stack.ppmtoolfullstack.domain.Backlog;
import io.tool.full.stack.ppmtoolfullstack.domain.ProjectTask;
import io.tool.full.stack.ppmtoolfullstack.exceptions.ProjectNotFoundException;
import io.tool.full.stack.ppmtoolfullstack.repositories.BacklogRepository;
import io.tool.full.stack.ppmtoolfullstack.repositories.ProjectTaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author badrikant.soni on 05/02/19
 */

@Service
@Slf4j
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectService projectService;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username) {

        //PTs to be added to a specific project, project != null, BL exists
        Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();

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

        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findBacklogById(String id, String username) {

        projectService.findProjectByIdentifier(id, username);
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id) {

        // Ensure to search on correct backlog
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if (backlog == null) {
            throw new ProjectNotFoundException("Project with ID : '" + backlog_id + "' does not exist");
        }

        // Ensure task is exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
        if (null == projectTask) {
            throw new ProjectNotFoundException("Project task '" + pt_id + "' not found");
        }

        // make sure that the backlog/project id in the path corresponds to the correct project
        if (!projectTask.getProjectIdentifier().equals(backlog_id)) {
            throw new ProjectNotFoundException("Project Task '" + pt_id + "' does not belong to project: '" + backlog_id);
        }
        return projectTask;
    }

    public ProjectTask updateProjectTask(ProjectTask projectTask, String backlog_id, String pt_id) {

        // find the existing ProjectTask
        ProjectTask existingProjectTask = findPTByProjectSequence(backlog_id, pt_id);
        if (!existingProjectTask.getProjectSequence().equals(projectTask.getProjectSequence())
                || !existingProjectTask.getProjectIdentifier().equals(projectTask.getProjectIdentifier())) {
            throw new ProjectNotFoundException("Project Task '" + projectTask.getProjectSequence()
                    + "' does not belong to project: '" + projectTask.getProjectIdentifier());
        }
        return projectTaskRepository.save(projectTask);
    }

    public void deleteProjectTask(String backlog_id, String pt_id) {
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);
        projectTaskRepository.delete(projectTask);
    }
}
