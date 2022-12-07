package org.economics.planningsystem.model.service.organization;

import org.economics.planningsystem.model.entity.organization.Organization;
import org.economics.planningsystem.model.entity.organization.Speciality;

import java.util.List;

public interface SpecialityService {
    List<Speciality> findSpecialitiesByOrganizationId(Long id);
    void deleteSpecialitiesById(Long id);
    void save(Speciality speciality);
    void save(Organization organization);

    Organization findOrganizationById(Long id);
}
