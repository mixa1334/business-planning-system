package org.economics.planningsystem.model.repository.organization;

import org.economics.planningsystem.model.entity.organization.Organization;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrganizationRepository extends Neo4jRepository<Organization, Long> {
    @Query("""
            MATCH (organization:ORGANIZATION) -[:HAS_EMPLOYEES]-> (employee_profile:EMPLOYEE_PROFILE)
            WHERE ID(employee_profile) = $id
            RETURN ID(organization)
            """)
    Optional<Long> findOrganizationIdByProfileId(@Param("id") Long id);
}
