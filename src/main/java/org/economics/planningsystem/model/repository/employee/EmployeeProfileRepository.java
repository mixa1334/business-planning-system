package org.economics.planningsystem.model.repository.employee;

import org.economics.planningsystem.model.entity.employee.EmployeeProfile;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface EmployeeProfileRepository extends Neo4jRepository<EmployeeProfile, Long> {
    @Query("""
            MATCH (e:EMPLOYEE_PROFILE)-[:HAS_TASKS]->(t:TASK)
            WHERE ID(e) = $empId AND ID(t) = $taskId
            WITH COUNT(e) > 0  as node_exists
            RETURN node_exists
            """)
    Boolean hasTask(Long empId, Long taskId);
}
