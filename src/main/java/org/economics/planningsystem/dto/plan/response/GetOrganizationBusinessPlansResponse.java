package org.economics.planningsystem.dto.plan.response;
import org.economics.planningsystem.model.entity.plan.BusinessPlan;

import java.util.List;

//from GET /organizations/{orgId}/business_plans
public class GetOrganizationBusinessPlansResponse {
    private List<BusinessPlan> businessPlans;

    public List<BusinessPlan> getBusinessPlans() {
        return businessPlans;
    }

    public void setBusinessPlans(List<BusinessPlan> businessPlans) {
        this.businessPlans = businessPlans;
    }
}
