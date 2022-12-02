package org.economics.planningsystem.dto.plan.request;

import java.math.BigDecimal;
import java.util.List;

// POST to /organizations/{orgId}/business_plans
public class CreateNewBusinessPlanRequest {
    private String title;

    private BigDecimal necessaryFunds;

    private String opportunity;

    private BigDecimal profit;

    private String deadline;
}
