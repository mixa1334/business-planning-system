package org.economics.planningsystem.model.repository.employee;

import org.economics.planningsystem.model.entity.employee.EmployeeProfile;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface EmployeeProfileRepository extends Neo4jRepository<EmployeeProfile, Long> {
}
