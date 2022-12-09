package org.economics.planningsystem.model.service.organization.impl;

import org.economics.planningsystem.model.entity.organization.Organization;
import org.economics.planningsystem.model.entity.organization.Speciality;
import org.economics.planningsystem.model.repository.organization.OrganizationRepository;
import org.economics.planningsystem.model.repository.organization.SpecialityRepository;
import org.economics.planningsystem.model.service.organization.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BasicSpecialityService implements SpecialityService {
    private final SpecialityRepository specialityRepository;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public BasicSpecialityService(SpecialityRepository repository, OrganizationRepository organizationRepository) {
        this.specialityRepository = repository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public List<Speciality> findSpecialitiesByOrganizationId(Long id) {
        return specialityRepository.findSpecialitiesByOrganizationId(id);
    }

    @Override
    public void createNewSpeciality(Long orgId, Speciality speciality) {
        Organization organization = organizationRepository.findById(orgId).orElseThrow();
        organization.getSpecialitiesOfOrganization().add(speciality);
        organizationRepository.save(organization);
    }

    @Override
    public void deleteSpecialityById(Long id) {
        specialityRepository.deleteById(id);
    }

    @Override
    public void deleteSpecialitiesById(Long id) {
        specialityRepository.deleteSpecialitiesById(id);
    }

    @Override
    public void save(Speciality speciality) {
        specialityRepository.save(speciality);
    }

    @Override
    public void save(Organization organization) {
        organizationRepository.save(organization);
    }

    @Override
    public Organization findOrganizationById(Long id) {
        return organizationRepository.findOrganizationById(id);
    }


}
