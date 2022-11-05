package org.economics.planningsystem.model.repository.organization;

import org.economics.planningsystem.model.entity.organization.Organization;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OrganizationRepository extends Neo4jRepository<Organization, Long> {
}
