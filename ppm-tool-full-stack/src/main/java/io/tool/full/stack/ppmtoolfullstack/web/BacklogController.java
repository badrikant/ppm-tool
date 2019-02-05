package io.tool.full.stack.ppmtoolfullstack.web;

import io.tool.full.stack.ppmtoolfullstack.domain.ProjectTask;
import io.tool.full.stack.ppmtoolfullstack.services.MapValidationErrorService;
import io.tool.full.stack.ppmtoolfullstack.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask, BindingResult result, @PathVariable String backlog_id) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if (errorMap != null) return errorMap;
        ProjectTask task = projectTaskService.addProjectTask(backlog_id, projectTask);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id) {
        return projectTaskService.findBacklogById(backlog_id);
    }
}
