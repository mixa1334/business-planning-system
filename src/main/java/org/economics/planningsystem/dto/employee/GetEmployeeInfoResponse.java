package org.economics.planningsystem.dto.employee;

import org.economics.planningsystem.model.entity.organization.Speciality;

// from GET /organizations/{orgId}/employees/{empId}
public class GetEmployeeInfoResponse {
    private String role;

    private Speciality speciality;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}
