package org.economics.planningsystem.controller.organization;

import org.economics.planningsystem.dto.organization.request.CreateNewSpecialityRequest;
import org.economics.planningsystem.dto.organization.response.GetOrganizationSpecialitiesResponse;
import org.economics.planningsystem.model.entity.organization.Organization;
import org.economics.planningsystem.model.entity.organization.Speciality;
import org.economics.planningsystem.model.service.organization.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
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
        GetOrganizationSpecialitiesResponse response = new GetOrganizationSpecialitiesResponse();
        List<Speciality> specialities = service.findSpecialitiesByOrganizationId(orgId);
        response.setSpecialities(specialities);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{specialityId}")
    public ResponseEntity<HttpStatus> deleteSpeciality(@PathVariable Long orgId, @PathVariable Long specialityId) {
        service.deleteSpecialitiesById(specialityId);
        return new ResponseEntity<>(HttpStatus.OK);
        // TODO: 12/1/2022 delete speciality by id

    }

    @PostMapping
    public ResponseEntity<HttpStatus> createNewSpecialityInOrganization(@PathVariable Long orgId, @RequestBody CreateNewSpecialityRequest createNewSpecialityRequest) {
        Organization organization = service.findOrganizationById(orgId);
        List<Speciality> specialities = new java.util.ArrayList<>(organization.getSpecialitiesOfOrganization().stream().toList());
        Speciality speciality = new Speciality();
        speciality.setDescription(createNewSpecialityRequest.getDescription());
        speciality.setName(createNewSpecialityRequest.getName());
        specialities.add(speciality);
        organization.setSpecialitiesOfOrganization(new HashSet<>(specialities));
        service.save(speciality);
        service.save(organization);
        return new ResponseEntity<>(HttpStatus.OK);
        // TODO: 12/1/2022 create new speciality in organization
    }
}
