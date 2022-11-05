package org.economics.planningsystem.model.repository.plan;

import org.economics.planningsystem.model.entity.plan.BusinessPlanSpecification;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BusinessPlanSpecificationRepository extends Neo4jRepository<BusinessPlanSpecification, Long> {
}
