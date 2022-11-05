package org.economics.planningsystem.model.repository.plan;

import org.economics.planningsystem.model.entity.plan.Task;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TaskRepository extends Neo4jRepository<Task, Long> {
}
