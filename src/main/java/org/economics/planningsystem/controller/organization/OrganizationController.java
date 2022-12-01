package org.economics.planningsystem.controller.organization;

import org.economics.planningsystem.dto.organization.request.ChangeOrganizationInfoRequest;
import org.economics.planningsystem.dto.organization.request.CreateNewOrganizationRequest;
import org.economics.planningsystem.dto.organization.request.RequestToJoinOrganization;
import org.economics.planningsystem.dto.organization.response.GetApplicationsForMembershipResponse;
import org.economics.planningsystem.dto.organization.response.GetOrganizationInfoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    @PostMapping
    public ResponseEntity<HttpStatus> createNewOrganization(@RequestBody CreateNewOrganizationRequest createNewOrganizationRequest) {
        // TODO: 12/1/2022 create new org; assign director_role (also create EmployeeProfile for User by id)
        //  of the one who created the organization (id included in request)
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOrganizationInfoResponse> getOrganizationInfo(@PathVariable Long id) {
        // TODO: 12/1/2022 get org info by id
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateOrganizationInfo(@PathVariable Long id, @RequestBody ChangeOrganizationInfoRequest changeOrganizationInfoRequest) {
        // TODO: 12/1/2022 update org info by id
        return null;
    }

    @PostMapping("/{orgId}/applications")
    public ResponseEntity<HttpStatus> sendRequestToJoinOrganization(@PathVariable Long orgId, @RequestBody RequestToJoinOrganization request) {
        // TODO: 12/1/2022 add user to List<users> in organization (aka list of applications to join org)
        return null;
    }

    @GetMapping("/{id}/applications")
    public ResponseEntity<GetApplicationsForMembershipResponse> getApplicationsForMembership(@PathVariable Long id) {
        // TODO: 12/1/2022 get all requests to join org by orgId
        return null;
    }

    @PostMapping("/{orgId}/applications/{userId}/accept")
    public ResponseEntity<HttpStatus> acceptNewEmployee(@PathVariable Long orgId, @PathVariable Long userId) {
        // TODO: 12/1/2022 remove employee from List<User> to List<Employees> in organization. Also create new Employee Profile for user
        return null;
    }

    @PostMapping("/{orgId}/applications/{userId}/reject")
    public ResponseEntity<HttpStatus> rejectNewEmployee(@PathVariable Long orgId, @PathVariable Long userId) {
        // TODO: 12/1/2022 remove employee from List<User> (aka Requests to join)
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOrganization(@PathVariable Long id) {
        // TODO: 12/1/2022 delete org by id; and delete all necessary info (like EmployeeProfiles, BusinessPlans, Tasks, etc.)
        return null;
    }
}
