package org.economics.planningsystem.model.service.organization.impl;

import org.economics.planningsystem.model.repository.organization.OrganizationRepository;
import org.economics.planningsystem.model.service.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class BasicOrganizationService implements OrganizationService {
    private final OrganizationRepository repository;

    @Autowired
    public BasicOrganizationService(OrganizationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Long> findOrganizationIdByProfileId(Long profileId) {
        return repository.findOrganizationIdByProfileId(profileId);
    }
}
