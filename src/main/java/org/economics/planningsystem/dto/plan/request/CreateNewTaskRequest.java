package org.economics.planningsystem.dto.plan.request;

import com.fasterxml.jackson.annotation.JsonProperty;

// POST to /organizations/{orgId}/business_plans/{planId}
public class CreateNewTaskRequest {
    private String title;

    private String description;

    private String deadline;

    @JsonProperty("necessary_speciality_id")
    private Integer necessarySpecialityId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Integer getNecessarySpecialityId() {
        return necessarySpecialityId;
    }

    public void setNecessarySpecialityId(Integer necessarySpecialityId) {
        this.necessarySpecialityId = necessarySpecialityId;
    }
}
