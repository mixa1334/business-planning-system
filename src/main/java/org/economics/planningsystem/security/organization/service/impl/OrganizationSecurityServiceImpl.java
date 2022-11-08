package org.economics.planningsystem.security.organization.service.impl;

import org.economics.planningsystem.model.repository.organization.OrganizationRepository;
import org.economics.planningsystem.security.organization.service.OrganizationSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrganizationSecurityServiceImpl implements OrganizationSecurityService {
    private final OrganizationRepository repository;

    @Autowired
    public OrganizationSecurityServiceImpl(OrganizationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Long> findOrganizationIdByProfileId(Long profileId) {
        return repository.findOrganizationIdByProfileId(profileId);
    }
}
