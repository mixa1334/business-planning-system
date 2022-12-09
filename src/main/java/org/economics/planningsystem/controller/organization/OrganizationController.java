package org.economics.planningsystem.controller.organization;

import org.economics.planningsystem.dto.organization.request.ChangeOrganizationInfoRequest;
import org.economics.planningsystem.dto.organization.request.CreateNewOrganizationRequest;
import org.economics.planningsystem.dto.organization.request.RequestToJoinOrganization;
import org.economics.planningsystem.dto.organization.response.GetApplicationsForMembershipResponse;
import org.economics.planningsystem.dto.organization.response.GetOrganizationInfoResponse;
import org.economics.planningsystem.dto.organization.response.GetOrganizationsResponse;
import org.economics.planningsystem.model.entity.employee.EmployeeProfile;
import org.economics.planningsystem.model.entity.employee.User;
import org.economics.planningsystem.model.entity.organization.Organization;
import org.economics.planningsystem.model.entity.organization.Speciality;
import org.economics.planningsystem.model.service.organization.SpecialityService;
import org.economics.planningsystem.model.service.organization.impl.BasicOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final BasicOrganizationService service;
    private final SpecialityService specialityService;

    @Autowired
    public OrganizationController(BasicOrganizationService service, SpecialityService specialityService) {
        this.service = service;
        this.specialityService = specialityService;
    }

    @GetMapping
    public ResponseEntity<GetOrganizationsResponse> getAllOrganizations() {
        GetOrganizationsResponse response = new GetOrganizationsResponse();
        response.setOrganizations(service.getAll());
        return ResponseEntity.ok(response);
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
        service.save(organization);
        organization.setEmployees(new HashSet<>(Collections.singletonList(user.getProfile())));
        Speciality speciality = new Speciality();
        speciality.setName("worker");
        speciality.setDescription("worker");
        organization.setSpecialitiesOfOrganization(new HashSet<>(Collections.singletonList(speciality)));
        employeeProfile.setSpeciality(speciality);
        employeeProfile.setOrganizationId(organization.getId());

        service.save(employeeProfile);
        service.save(user);
        service.save(organization);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('EMPLOYEE') and isInOrganization(#id)")
    @GetMapping("/{id}")
    public ResponseEntity<GetOrganizationInfoResponse> getOrganizationInfo(@PathVariable Long id) {
        GetOrganizationInfoResponse organizationInfoResponse = new GetOrganizationInfoResponse();
        Organization organization = service.findOrganizationById(id);
        organizationInfoResponse.setOrganization(organization);
        return ResponseEntity.ok(organizationInfoResponse);
    }

    @PreAuthorize("hasAuthority('DIRECTOR') and isInOrganization(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateOrganizationInfo(@PathVariable Long id, @RequestBody ChangeOrganizationInfoRequest changeOrganizationInfoRequest) {
        Organization organization = service.updateById(id, changeOrganizationInfoRequest);
        if (organization == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.save(organization);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{orgId}/applications")
    public ResponseEntity<HttpStatus> sendRequestToJoinOrganization(@PathVariable Long orgId, @RequestBody RequestToJoinOrganization request) {
        Organization organization = service.findOrganizationById(orgId);
        User user = service.findUserById(Long.valueOf(request.getUserId()));
        organization.getApplicationForMembership().add(user);
        service.save(organization);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DIRECTOR') and isInOrganization(#id)")
    @GetMapping("/{id}/applications")
    public ResponseEntity<GetApplicationsForMembershipResponse> getApplicationsForMembership(@PathVariable Long id) {
        Organization organization = service.findOrganizationById(id);
        Set<User> users = organization.getApplicationForMembership();
        GetApplicationsForMembershipResponse applications = new GetApplicationsForMembershipResponse();
        applications.setApplicationForMembership(new ArrayList<>(users));
        return ResponseEntity.ok(applications);
    }

    @PreAuthorize("hasAuthority('DIRECTOR') and isInOrganization(#orgId)")
    @PostMapping("/{orgId}/applications/{userId}/accept")
    public ResponseEntity<HttpStatus> acceptNewEmployee(@PathVariable Long orgId, @PathVariable Long userId) {
        Organization organization = service.findOrganizationById(orgId);

        User userOptional = organization.getApplicationForMembership().stream().filter(user -> user.getId().equals(userId)).findAny().get();
        organization.getApplicationForMembership().remove(userOptional);

        EmployeeProfile employeeProfile = new EmployeeProfile();
        employeeProfile.setRole(EmployeeProfile.EmployeeRole.EMPLOYEE);
        employeeProfile.setSpeciality(specialityService.findSpecialitiesByOrganizationId(orgId).get(0));
        employeeProfile.setOrganizationId(organization.getId());
        userOptional.setProfile(employeeProfile);
        organization.getEmployees().add(userOptional.getProfile());

        service.save(userOptional);
        service.save(organization);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DIRECTOR') and isInOrganization(#orgId)")
    @PostMapping("/{orgId}/applications/{userId}/reject_user")
    public ResponseEntity<HttpStatus> rejectNewEmployee(@PathVariable Long orgId, @PathVariable Long userId) {
        Organization organization = service.findOrganizationById(orgId);

        User userOptional = organization.getApplicationForMembership().stream().filter(user -> user.getId().equals(userId)).findAny().get();
        organization.getApplicationForMembership().remove(userOptional);
        service.save(userOptional);
        service.save(organization);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DIRECTOR') and isInOrganization(#id)")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOrganization(@PathVariable Long id) {
        service.deleteOrganizationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
