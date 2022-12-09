package org.economics.planningsystem.controller.organization;

import org.economics.planningsystem.dto.organization.request.ChangeOrganizationInfoRequest;
import org.economics.planningsystem.dto.organization.request.CreateNewOrganizationRequest;
import org.economics.planningsystem.dto.organization.request.RequestToJoinOrganization;
import org.economics.planningsystem.dto.organization.response.GetApplicationsForMembershipResponse;
import org.economics.planningsystem.dto.organization.response.GetOrganizationInfoResponse;
import org.economics.planningsystem.model.entity.employee.EmployeeProfile;
import org.economics.planningsystem.model.entity.employee.User;
import org.economics.planningsystem.model.entity.organization.Organization;
import org.economics.planningsystem.model.service.organization.impl.BasicOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final BasicOrganizationService service;

    @Autowired
    public OrganizationController(BasicOrganizationService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createNewOrganization(@RequestBody CreateNewOrganizationRequest createNewOrganizationRequest) {
        EmployeeProfile employeeProfile = new EmployeeProfile();
        employeeProfile.setRole(EmployeeProfile.EmployeeRole.DIRECTOR);

        User user = service.findUserById(createNewOrganizationRequest.getUserId());
        user.setProfile(employeeProfile);

        Organization organization = new Organization();
        organization.setAvailableFunds(createNewOrganizationRequest.getFunds());
        organization.setName(createNewOrganizationRequest.getName());
        organization.setEmployees(new HashSet<>(Collections.singletonList(user.getProfile())));

        service.save(employeeProfile);
        service.save(user);
        service.save(organization);
        return new ResponseEntity<>(HttpStatus.OK);
        // TODO: 12/1/2022 create new org; assign director_role (also create EmployeeProfile for User by id)
        //  of the one who created the organization (id included in request)
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOrganizationInfoResponse> getOrganizationInfo(@PathVariable Long id) {
        GetOrganizationInfoResponse organizationInfoResponse = new GetOrganizationInfoResponse();
        Organization organization = service.findOrganizationById(id);
        organizationInfoResponse.setOrganization(organization);
        return ResponseEntity.ok(organizationInfoResponse);
        // TODO: 12/1/2022 get org info by id
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateOrganizationInfo(@PathVariable Long id, @RequestBody ChangeOrganizationInfoRequest changeOrganizationInfoRequest) {
        Organization organization = service.updateById(id, changeOrganizationInfoRequest);
        if (organization == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.save(organization);
        return new ResponseEntity<>(HttpStatus.OK);
        // TODO: 12/1/2022 update org info by id
    }

    @PostMapping("/{orgId}/applications")
    public ResponseEntity<HttpStatus> sendRequestToJoinOrganization(@PathVariable Long orgId, @RequestBody RequestToJoinOrganization request) {
        Organization organization = service.findOrganizationById(orgId);
        User user = service.findUserById(Long.valueOf(request.getUserId()));
        organization.getApplicationForMembership().add(user);
        service.save(organization);
        return new ResponseEntity<>(HttpStatus.OK);
        // TODO: 12/1/2022 add user to List<users> in organization (aka list of applications to join org)
    }

    @GetMapping("/{id}/applications")
    public ResponseEntity<GetApplicationsForMembershipResponse> getApplicationsForMembership(@PathVariable Long id) {
        Organization organization = service.findOrganizationById(id);
        Set<User> users = organization.getApplicationForMembership();
        GetApplicationsForMembershipResponse applications = new GetApplicationsForMembershipResponse();
        applications.setApplicationForMembership(new ArrayList<>(users));
        return ResponseEntity.ok(applications);
        // TODO: 12/1/2022 get all requests to join org by orgId
    }

    @PostMapping("/{orgId}/applications/{userId}/accept")
    public ResponseEntity<HttpStatus> acceptNewEmployee(@PathVariable Long orgId, @PathVariable Long userId) {
        Organization organization = service.findOrganizationById(orgId);

        User userOptional = organization.getApplicationForMembership().stream().filter(user -> user.getId().equals(userId)).findAny().get();
        organization.getApplicationForMembership().remove(userOptional);

        userOptional.setProfile(new EmployeeProfile());
        organization.getEmployees().add(userOptional.getProfile());

        service.save(userOptional);
        service.save(organization);
        return new ResponseEntity<>(HttpStatus.OK);
        // TODO: 12/1/2022 remove employee from List<User> to List<Employees> in organization. Also create new Employee Profile for user

    }

    @PostMapping("/{orgId}/applications/{userId}/reject")
    public ResponseEntity<HttpStatus> rejectNewEmployee(@PathVariable Long orgId, @PathVariable Long userId) {
        Organization organization = service.findOrganizationById(orgId);

        User userOptional = organization.getApplicationForMembership().stream().filter(user -> user.getId().equals(userId)).findAny().get();
        organization.getApplicationForMembership().remove(userOptional);
        service.save(userOptional);
        service.save(organization);
        return new ResponseEntity<>(HttpStatus.OK);
        // TODO: 12/1/2022 remove employee from List<User> (aka Requests to join)
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOrganization(@PathVariable Long id) {
        service.deleteOrganizationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
        // TODO: 12/1/2022 delete org by id; and delete all necessary info (like EmployeeProfiles, BusinessPlans, Tasks, etc.)
    }
}
