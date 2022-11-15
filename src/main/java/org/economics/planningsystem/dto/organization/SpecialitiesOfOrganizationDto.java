package org.economics.planningsystem.dto.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.economics.planningsystem.model.entity.organization.Speciality;

import java.util.List;

public class SpecialitiesOfOrganizationDto {
    @JsonProperty("organization_id")
    private Long organizationId;

    private List<Speciality> specialities;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
}
