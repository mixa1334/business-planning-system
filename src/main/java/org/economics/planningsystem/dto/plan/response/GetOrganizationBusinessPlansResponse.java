package org.economics.planningsystem.dto.plan.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.economics.planningsystem.model.entity.plan.BusinessPlan;

import java.util.List;

//from GET /organizations/{orgId}/business_plans
public class GetOrganizationBusinessPlansResponse {
    @JsonProperty("business_plans")
    private List<BusinessPlan> businessPlans;

    public List<BusinessPlan> getBusinessPlans() {
        return businessPlans;
    }

    public void setBusinessPlans(List<BusinessPlan> businessPlans) {
        this.businessPlans = businessPlans;
    }
}
