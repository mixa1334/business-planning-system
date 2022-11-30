package org.economics.planningsystem.dto.organization;

import org.economics.planningsystem.model.entity.organization.Speciality;

import java.util.List;

// from GET /organizations/{orgId}/specialities
public class GetOrganizationSpecialitiesResponse {
    private List<Speciality> specialities;

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
}
