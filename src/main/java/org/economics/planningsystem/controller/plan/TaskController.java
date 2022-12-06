package org.economics.planningsystem.controller.plan;

import org.economics.planningsystem.dto.plan.request.CreateNewTaskRequest;
import org.economics.planningsystem.model.service.plan.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/organizations/{orgId}/business_plans/{planId}/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<HttpStatus> completeTask(
            @PathVariable Long orgId,
            @PathVariable Long planId,
            @PathVariable Long taskId
    ) {
        taskService.complete(orgId, planId, taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createNewTask(
            @PathVariable Long orgId,
            @PathVariable Long planId,
            @RequestBody CreateNewTaskRequest request
    ) {
        taskService.create(orgId, planId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
