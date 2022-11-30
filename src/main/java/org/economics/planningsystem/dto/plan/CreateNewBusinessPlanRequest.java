package org.economics.planningsystem.dto.plan;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

// POST to /organizations/{orgId}/business_plans
public class CreateNewBusinessPlanRequest {
    private String title;

    @JsonProperty("necessary_funds")
    private BigDecimal necessaryFunds;

    private String opportunity;

    private BigDecimal profit;

    private String deadline;

    private List<CreateNewTaskRequest> tasks;
}
