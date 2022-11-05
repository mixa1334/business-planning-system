package org.economics.planningsystem.model.repository.organization;

import org.economics.planningsystem.model.entity.organization.Speciality;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialityRepository extends Neo4jRepository<Speciality, Long> {
    @Query("""
            MATCH (organization:ORGANIZATION) -[:HAS_SPECIALITIES]-> (speciality:SPECIALITY)
            WHERE ID(organization) = $id
            RETURN speciality
            """)
    List<Speciality> findSpecialitiesByOrganizationId(@Param("id") Long id);
}
