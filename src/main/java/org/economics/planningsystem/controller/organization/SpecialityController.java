package org.economics.planningsystem.controller.organization;

import org.economics.planningsystem.dto.organization.request.CreateNewSpecialityRequest;
import org.economics.planningsystem.dto.organization.response.GetOrganizationSpecialitiesResponse;
import org.economics.planningsystem.model.entity.organization.Speciality;
import org.economics.planningsystem.model.service.organization.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/organizations/{orgId}/specialities")
public class SpecialityController {
    private final SpecialityService service;

    @Autowired
    public SpecialityController(SpecialityService service) {
        this.service = service;
    }

    @PreAuthorize("isAMemberOfOrganization(#orgId)")
    @GetMapping
    public ResponseEntity<GetOrganizationSpecialitiesResponse> getAllSpecialitiesInOrganization(@PathVariable Long orgId) {
        GetOrganizationSpecialitiesResponse response = new GetOrganizationSpecialitiesResponse();
        List<Speciality> specialities = service.findSpecialitiesByOrganizationId(orgId);
        response.setSpecialities(specialities);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('DIRECTOR') and isAMemberOfOrganization(#orgId)")
    @DeleteMapping("/{specialityId}")
    public ResponseEntity<HttpStatus> deleteSpeciality(@PathVariable Long orgId, @PathVariable Long specialityId) {
        service.deleteSpecialityById(specialityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('DIRECTOR') and isAMemberOfOrganization(#orgId)")
    @PostMapping
    public ResponseEntity<HttpStatus> createNewSpecialityInOrganization(
            @PathVariable Long orgId,
            @RequestBody CreateNewSpecialityRequest createNewSpecialityRequest
    ) {
        Speciality newSpeciality = createNewSpecialityRequest.toSpeciality();
        service.createNewSpeciality(orgId, newSpeciality);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
