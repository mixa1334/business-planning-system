package org.economics.planningsystem.controller.plan;

import org.economics.planningsystem.dto.plan.request.CreateNewTaskRequest;
import org.economics.planningsystem.model.service.plan.TaskService;
import org.economics.planningsystem.security.user.BpsUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PreAuthorize("hasAuthority('EMPLOYEE') and isInOrganization(#orgId)")
    @PutMapping("/{taskId}")
    public ResponseEntity<HttpStatus> completeTask(
            @PathVariable Long orgId,
            @PathVariable Long planId,
            @PathVariable Long taskId
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BpsUserDetails userDetails = (BpsUserDetails) authentication.getPrincipal();
        taskService.complete(orgId, planId, taskId, userDetails.getProfileId(), userDetails.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ANALYST') and isInOrganization(#orgId)")
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
