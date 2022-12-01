package org.economics.planningsystem.controller.plan;

import org.economics.planningsystem.dto.plan.request.CreateNewTaskRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/organizations/{orgId}/business_plans/{planId}/tasks")
public class TaskController {

    @PutMapping("/{taskId}")
    public ResponseEntity<HttpStatus> completeTask(@PathVariable Long orgId, @PathVariable Long planId, @PathVariable Long taskId) {
        // TODO: 12/1/2022 change task status and change business plan statistics (completed tasks, completed on time, etc.)
        return null;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createNewTask(@PathVariable Long orgId, @PathVariable Long planId, @RequestBody CreateNewTaskRequest createNewTaskRequest) {
        // TODO: 12/1/2022 create new task in business plan. Also assign this task to employee who has the required qualification (in request)
        return null;
    }
}
