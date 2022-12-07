package org.economics.planningsystem.model.service.organization.impl;

import org.economics.planningsystem.dto.organization.request.ChangeOrganizationInfoRequest;
import org.economics.planningsystem.model.entity.employee.EmployeeProfile;
import org.economics.planningsystem.model.entity.employee.User;
import org.economics.planningsystem.model.entity.organization.Organization;
import org.economics.planningsystem.model.repository.employee.EmployeeProfileRepository;
import org.economics.planningsystem.model.repository.employee.UserRepository;
import org.economics.planningsystem.model.repository.organization.OrganizationRepository;
import org.economics.planningsystem.model.service.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class BasicOrganizationService implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final EmployeeProfileRepository employeeRepository;

    @Autowired
    public BasicOrganizationService(OrganizationRepository repository, UserRepository userRepository, EmployeeProfileRepository employeeRepository) {
        this.organizationRepository = repository;
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Long findOrganizationIdByProfileId(Long profileId) {
        return organizationRepository.findOrganizationIdByProfileId(profileId);
    }

    @Override
    public Organization findOrganizationById(Long id) {
        return organizationRepository.findOrganizationById(id);
    }

    @Override
    public void save(Organization organization) {
        organizationRepository.save(organization);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void save(EmployeeProfile employeeProfile) {
        employeeRepository.save(employeeProfile);
    }

    @Override
    public Organization updateById(Long id, ChangeOrganizationInfoRequest organizationInfoRequest) {
        Organization organization = organizationRepository.findOrganizationById(id);
        if (organization == null){
            return null;
        }
        organization.setName(organizationInfoRequest.getNewName());
        organization.setAvailableFunds(organizationInfoRequest.getNewFunds());
        organizationRepository.save(organization);
        return organization;
    }

    @Override
    public void deleteOrganizationById(Long id) {
        organizationRepository.deleteOrganization(id);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }


}
