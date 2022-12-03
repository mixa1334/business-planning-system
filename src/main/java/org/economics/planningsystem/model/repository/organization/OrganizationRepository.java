package org.economics.planningsystem.model.repository.organization;

import org.economics.planningsystem.model.entity.organization.Organization;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationRepository extends Neo4jRepository<Organization, Long> {
    @Query("""
            MATCH (organization:ORGANIZATION) -[:HAS_EMPLOYEES]-> (employee_profile:EMPLOYEE_PROFILE)
            WHERE ID(employee_profile) = $id
            RETURN ID(organization)
            """)
    Long findOrganizationIdByProfileId(@Param("id") Long id);
    Organization findOrganizationById(Long id);
    //void deleteOrganizationById();
    @Query("""
            MATCH (organization:ORGANIZATION)
            WHERE ID(organization) = $id
            DETACH DELETE organization
            """)
    void deleteOrganization(@Param("id") Long id);


}
