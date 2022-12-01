package org.economics.planningsystem.controller.employee;

import org.economics.planningsystem.dto.employee.request.ChangeEmployeeInfoRequest;
import org.economics.planningsystem.dto.employee.response.GetEmployeeInfoResponse;
import org.economics.planningsystem.dto.organization.response.GetOrganizationEmployeeInfoResponse;
import org.economics.planningsystem.dto.plan.response.GetEmployeeTasksResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/organizations/{orgId}/employees")
public class EmployeeController {
    @GetMapping
    public ResponseEntity<GetOrganizationEmployeeInfoResponse> getOrganizationEmployeeInfo(@PathVariable Long orgId) {
        // TODO: 12/1/2022 get all org employees by orgId
        return null;
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<HttpStatus> leaveOrganization(@PathVariable Long orgId, @PathVariable Long empId) {
        // TODO: 12/1/2022 delete EmployeeProfile, redistribute tasks (tasks which belongs to current empl) to other employees in organization
        return null;
    }

    @GetMapping("/{empId}")
    public ResponseEntity<GetEmployeeInfoResponse> getEmployeeInfo(@PathVariable Long orgId, @PathVariable Long empId) {
        // TODO: 12/1/2022 get employee info by id
        return null;
    }

    @PutMapping("/{empId}")
    public ResponseEntity<HttpStatus> updateEmployeeInfo(@PathVariable Long orgId, @PathVariable Long empId, @RequestBody ChangeEmployeeInfoRequest changeEmployeeInfoRequest) {
        // TODO: 12/1/2022 update employee info by id
        return null;
    }

    @GetMapping("/{empId}/tasks")
    public ResponseEntity<GetEmployeeTasksResponse> getEmployeeTasks(@PathVariable Long orgId, @PathVariable Long empId) {
        // TODO: 12/1/2022 return all tasks of employee
        return null;
    }
}
