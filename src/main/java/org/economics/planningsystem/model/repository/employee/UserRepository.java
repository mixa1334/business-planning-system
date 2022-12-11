package org.economics.planningsystem.model.repository.employee;

import org.economics.planningsystem.model.entity.employee.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User, Long> {
    Optional<User> findUserByLogin(String login);

    Boolean existsByLogin(String login);
}