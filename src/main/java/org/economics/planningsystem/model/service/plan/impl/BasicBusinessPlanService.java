package org.economics.planningsystem.model.service.plan.impl;

import org.economics.planningsystem.model.entity.organization.Organization;
import org.economics.planningsystem.model.entity.plan.BusinessPlan;
import org.economics.planningsystem.model.repository.organization.OrganizationRepository;
import org.economics.planningsystem.model.service.plan.BusinessPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BasicBusinessPlanService implements BusinessPlanService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    public BasicBusinessPlanService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Long create(Long orgId, BusinessPlan plan) {
        Organization organization = organizationRepository.findById(orgId).orElseThrow();
        organization.getBusinessPlans().add(plan);
        organizationRepository.save(organization);
        return plan.getId();
    }

    @Override
    public List<BusinessPlan> findAll(Long orgId) {
        return organizationRepository.findById(orgId)
                .orElseThrow()
                .getBusinessPlans()
                .stream().toList();
    }
}
