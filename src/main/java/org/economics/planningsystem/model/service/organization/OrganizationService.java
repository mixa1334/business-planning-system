package org.economics.planningsystem.model.service.organization;

import org.economics.planningsystem.dto.organization.request.ChangeOrganizationInfoRequest;
import org.economics.planningsystem.model.entity.employee.EmployeeProfile;
import org.economics.planningsystem.model.entity.employee.User;
import org.economics.planningsystem.model.entity.organization.Organization;

import java.util.List;


public interface OrganizationService {
    List<Organization> getAll();

    Organization findOrganizationById(Long id);

    void save(Organization organization);

    void save(User user);

    void save(EmployeeProfile employeeProfile);

    Organization updateById(Long id, ChangeOrganizationInfoRequest organizationInfoRequest);

    void deleteOrganizationById(Long id);

    User findUserById(Long id);
}
