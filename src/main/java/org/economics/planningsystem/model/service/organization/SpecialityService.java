package org.economics.planningsystem.model.service.organization;

import org.economics.planningsystem.model.entity.organization.Speciality;

import java.util.List;

public interface SpecialityService {
    List<Speciality> findSpecialitiesByOrganizationId(Long id);

    void createNewSpeciality(Long orgId, Speciality speciality);

    void deleteSpecialityById(Long id);
}
