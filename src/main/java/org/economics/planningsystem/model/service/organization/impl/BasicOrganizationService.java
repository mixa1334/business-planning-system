package org.economics.planningsystem.model.service.organization.impl;

import org.economics.planningsystem.dto.organization.request.ChangeOrganizationInfoRequest;
import org.economics.planningsystem.model.entity.organization.Organization;
import org.economics.planningsystem.model.repository.organization.OrganizationRepository;
import org.economics.planningsystem.model.service.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;

@Service
@Transactional(rollbackFor = Exception.class)
public class BasicOrganizationService implements OrganizationService {
    private final OrganizationRepository repository;

    @Autowired
    public BasicOrganizationService(OrganizationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long findOrganizationIdByProfileId(Long profileId) {
        return repository.findOrganizationIdByProfileId(profileId);
    }

    @Override
    public Organization findOrganizationById(Long id) {
        return repository.findOrganizationById(id);
    }

    @Override
    public void save(Organization organization) {
        repository.save(organization);
    }

    @Override
    public Organization updateById(Long id, ChangeOrganizationInfoRequest organizationInfoRequest) {
        Organization organization = repository.findOrganizationById(id);
        if (organization == null){
            return null;
        }
        organization.setName(organizationInfoRequest.getNewName());
        organization.setAvailableFunds(organizationInfoRequest.getNewFunds());
        repository.save(organization);
        return organization;
    }

    @Override
    public void deleteOrganizationById(Long id) {
        repository.deleteOrganization(id);
    }


}
