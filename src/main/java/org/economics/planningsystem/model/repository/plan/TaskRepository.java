package org.economics.planningsystem.model.repository.plan;

import org.economics.planningsystem.model.entity.plan.Task;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface TaskRepository extends Neo4jRepository<Task, Long> {
    @Query("""
            MATCH (:EMPLOYEE_PROFILE) -[has_task:HAS_TASKS]-> (task:TASK)
            WHERE ID(task) = $taskId
            DELETE has_task
            """)
    void deleteTaskFromEmployee(Long taskId);
}
