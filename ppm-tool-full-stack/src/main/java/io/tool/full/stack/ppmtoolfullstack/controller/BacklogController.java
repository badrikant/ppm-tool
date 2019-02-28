package io.tool.full.stack.ppmtoolfullstack.controller;

import io.tool.full.stack.ppmtoolfullstack.domain.ProjectTask;
import io.tool.full.stack.ppmtoolfullstack.services.MapValidationErrorService;
import io.tool.full.stack.ppmtoolfullstack.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

/**
 * @author badrikant.soni on 05/02/19
 */

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {


    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private ProjectTaskService projectTaskService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask, BindingResult result, @PathVariable String backlog_id, Principal principal) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if (errorMap != null) return errorMap;
        ProjectTask task = projectTaskService.addProjectTask(backlog_id, projectTask, principal.getName());
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id, Principal principal) {
        return projectTaskService.findBacklogById(backlog_id, principal.getName());
    }

    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<ProjectTask> getProjectTaskBySeq(@PathVariable String backlog_id, @PathVariable String pt_id, Principal principal) {
        ProjectTask projectTask = projectTaskService.findPTByProjectSequence(backlog_id, pt_id, principal.getName());
        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
    }

    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result, @PathVariable String backlog_id, @PathVariable String pt_id, Principal principal) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if (errorMap != null) return errorMap;

        ProjectTask updateProjectTask = projectTaskService.updateProjectTask(projectTask, backlog_id, pt_id, principal.getName());
        return new ResponseEntity<>(updateProjectTask, HttpStatus.OK);
    }

    @DeleteMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id, Principal principal) {
        projectTaskService.deleteProjectTask(backlog_id, pt_id, principal.getName());
        return new ResponseEntity<>("Project Task " + pt_id + " was deleted successfully", HttpStatus.OK);
    }
}