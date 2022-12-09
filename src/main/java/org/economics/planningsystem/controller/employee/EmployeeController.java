package org.economics.planningsystem.controller.employee;

import org.economics.planningsystem.dto.employee.request.ChangeEmployeeInfoRequest;
import org.economics.planningsystem.dto.employee.response.GetEmployeeInfoResponse;
import org.economics.planningsystem.dto.organization.response.GetOrganizationEmployeeInfoResponse;
import org.economics.planningsystem.dto.plan.response.GetEmployeeTasksResponse;
import org.economics.planningsystem.model.entity.employee.EmployeeProfile;
import org.economics.planningsystem.model.entity.plan.Task;
import org.economics.planningsystem.model.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/organizations/{orgId}/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasAuthority('DIRECTOR') and isInOrganization(#orgId)")
    @GetMapping
    public ResponseEntity<GetOrganizationEmployeeInfoResponse> getOrganizationEmployeeInfo(@PathVariable Long orgId) {
        GetOrganizationEmployeeInfoResponse response = new GetOrganizationEmployeeInfoResponse();
        List<EmployeeProfile> employees = employeeService.findAll(orgId);
        response.setEmployees(employees);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE') and isInOrganization(#orgId)")
    @DeleteMapping("/{empId}")
    public ResponseEntity<HttpStatus> leaveOrganization(@PathVariable Long orgId, @PathVariable Long empId) {
        employeeService.delete(orgId, empId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE') and isInOrganization(#orgId)")
    @GetMapping("/{empId}")
    public ResponseEntity<GetEmployeeInfoResponse> getEmployeeInfo(@PathVariable Long orgId, @PathVariable Long empId) {
        GetEmployeeInfoResponse response = new GetEmployeeInfoResponse();
        EmployeeProfile employee = employeeService.findById(orgId, empId);
        response.setSpeciality(employee.getSpeciality());
        response.setRole(employee.getRole().name());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE') and isInOrganization(#orgId)")
    @PutMapping("/{empId}")
    public ResponseEntity<HttpStatus> updateEmployeeInfo(
            @PathVariable Long orgId,
            @PathVariable Long empId,
            @RequestBody ChangeEmployeeInfoRequest request) {
        employeeService.update(orgId, empId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE') and isInOrganization(#orgId)")
    @GetMapping("/{empId}/tasks")
    public ResponseEntity<GetEmployeeTasksResponse> getEmployeeTasks(@PathVariable Long orgId, @PathVariable Long empId) {
        GetEmployeeTasksResponse response = new GetEmployeeTasksResponse();
        List<Task> tasks = employeeService.getAllTasks(orgId, empId);
        response.setTasks(tasks);
        return ResponseEntity.ok(response);
    }
}
