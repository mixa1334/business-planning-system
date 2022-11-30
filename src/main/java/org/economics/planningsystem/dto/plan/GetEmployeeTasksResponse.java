package org.economics.planningsystem.dto.plan;

import org.economics.planningsystem.model.entity.plan.Task;

import java.util.List;

// from GET /organizations/{id}/employee/{id}/tasks
public class GetEmployeeTasksResponse {
    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
