package org.economics.planningsystem.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

// PUT to /organizations/{orgId}/employees/{employeeId}
public class ChangeEmployeeInfoRequest {
    @JsonProperty("speciality_id")
    private Integer specialityId;

    private String role;

    public Integer getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Integer specialityId) {
        this.specialityId = specialityId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
