package org.economics.planningsystem.dto.plan.response;

import com.fasterxml.jackson.annotation.JsonProperty;

//from POST /organizations/{orgId}/business_plans
public class CreateNewBusinessPlanResponse {
    @JsonProperty("plan_id")
    private Long planId;

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }
}
