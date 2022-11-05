package org.economics.planningsystem.model.repository.employee;

import org.economics.planningsystem.model.entity.employee.EmployeeStatistics;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface EmployeeStatisticsRepository extends Neo4jRepository<EmployeeStatistics, Long> {
}
