package org.economics.planningsystem.model.service.organization.impl;

import org.economics.planningsystem.model.entity.organization.Speciality;
import org.economics.planningsystem.model.repository.organization.SpecialityRepository;
import org.economics.planningsystem.model.service.organization.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BasicSpecialityService implements SpecialityService {
    private final SpecialityRepository repository;

    @Autowired
    public BasicSpecialityService(SpecialityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Speciality> findSpecialitiesByOrganizationId(Long id) {
        return repository.findSpecialitiesByOrganizationId(id);
    }

    @Override
    public void deleteSpecialitiesById(Long id) {
        repository.deleteSpecialitiesById(id);
    }
}
