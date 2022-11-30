package org.economics.planningsystem.dto.organization;

import org.economics.planningsystem.model.entity.employee.EmployeeProfile;

import java.util.List;

//from GET /organizations/{orgId}/employees
public class GetOrganizationEmployeeInfoResponse {
    private List<EmployeeProfile> employees;

    public List<EmployeeProfile> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeProfile> employees) {
        this.employees = employees;
    }
}
