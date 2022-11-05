package org.economics.planningsystem.model.repository.plan;

import org.economics.planningsystem.model.entity.plan.BusinessPlanStatistics;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BusinessPlanStatisticsRepository extends Neo4jRepository<BusinessPlanStatistics, Long> {
}
