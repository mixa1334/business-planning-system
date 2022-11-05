package org.economics.planningsystem.model.repository.plan;

import org.economics.planningsystem.model.entity.plan.BusinessPlan;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BusinessPlanRepository extends Neo4jRepository<BusinessPlan, Long> {
}
