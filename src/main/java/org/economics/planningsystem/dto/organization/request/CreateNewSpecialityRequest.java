package org.economics.planningsystem.dto.organization.request;

import org.economics.planningsystem.model.entity.organization.Speciality;

// POST to /organizations/{orgId}/specialities
public class CreateNewSpecialityRequest {
    private String name;

    private String description;

    public Speciality toSpeciality() {
        Speciality speciality = new Speciality();
        speciality.setName(name);
        speciality.setDescription(description);
        return speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
