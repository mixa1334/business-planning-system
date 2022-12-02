package org.economics.planningsystem.dto.employee.request;

// PUT to /organizations/{orgId}/employees/{employeeId}
public class ChangeEmployeeInfoRequest {
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
