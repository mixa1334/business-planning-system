package org.economics.planningsystem.dto.plan.response;

//from POST /organizations/{orgId}/business_plans
public class CreateNewBusinessPlanResponse {
    private Long planId;

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }
}
