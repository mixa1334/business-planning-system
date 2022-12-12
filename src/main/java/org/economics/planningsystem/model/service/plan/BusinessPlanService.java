package org.economics.planningsystem.model.service.plan;

import org.economics.planningsystem.model.entity.plan.BusinessPlan;

import java.util.List;

public interface BusinessPlanService {
    Long create(Long orgId, BusinessPlan plan);

    List<BusinessPlan> findAll(Long orgId);
}
