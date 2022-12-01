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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/organizations/{orgId}/specialities")
public class SpecialityController {
    private final SpecialityService service;

    @Autowired
    public SpecialityController(SpecialityService service) {
        this.service = service;
        // TODO: 12/1/2022
    }

    @GetMapping
    public ResponseEntity<GetOrganizationSpecialitiesResponse> getAllSpecialitiesInOrganization(@PathVariable Long orgId) {
        // TODO: 12/1/2022 get all org specialities
        return null;
    }

    @DeleteMapping("/{specialityId}")
    public ResponseEntity<HttpStatus> deleteSpeciality(@PathVariable Long orgId, @PathVariable Long specialityId) {
        // TODO: 12/1/2022 delete speciality by id
        return null;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createNewSpecialityInOrganization(@PathVariable Long orgId, @RequestBody CreateNewSpecialityRequest createNewSpecialityRequest) {
        // TODO: 12/1/2022 create new speciality in organization
        return null;
    }
}
