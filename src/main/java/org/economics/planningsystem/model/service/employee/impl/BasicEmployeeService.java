package org.economics.planningsystem.model.service.employee.impl;

import org.economics.planningsystem.dto.employee.request.ChangeEmployeeInfoRequest;
import org.economics.planningsystem.model.entity.employee.EmployeeProfile;
import org.economics.planningsystem.model.entity.organization.Organization;
import org.economics.planningsystem.model.entity.organization.Speciality;
import org.economics.planningsystem.model.entity.plan.Task;
import org.economics.planningsystem.model.repository.employee.EmployeeProfileRepository;
import org.economics.planningsystem.model.repository.organization.OrganizationRepository;
import org.economics.planningsystem.model.repository.organization.SpecialityRepository;
import org.economics.planningsystem.model.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class BasicEmployeeService implements EmployeeService {

    private final OrganizationRepository organizationRepository;
    private final EmployeeProfileRepository employeeProfileRepository;
    private final SpecialityRepository specialityRepository;

    @Autowired
    public BasicEmployeeService(OrganizationRepository organizationRepository,
                                EmployeeProfileRepository employeeProfileRepository,
                                SpecialityRepository specialityRepository) {
        this.organizationRepository = organizationRepository;
        this.employeeProfileRepository = employeeProfileRepository;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public List<EmployeeProfile> findAll(Long orgId) {
        Organization organization = organizationRepository.findById(orgId).orElseThrow();
        return organization.getEmployees().stream().toList();
    }

    @Override
    public void delete(Long orgId, Long empId) {
        EmployeeProfile employeeProfile = employeeProfileRepository.findById(empId).orElseThrow();
        Set<Task> tasks = employeeProfile.getTasks();

        Speciality necessarySpeciality = employeeProfile.getSpeciality();
        List<EmployeeProfile> employees = new ArrayList<>(findAll(orgId));
        employees.remove(employeeProfile);

        employees.stream()
                .filter(e -> e.getSpeciality().equals(necessarySpeciality))
                .min(Comparator.comparing(e -> e.getTasks().size()))
                .ifPresentOrElse(e -> {
                            e.getTasks().addAll(tasks);
                            employeeProfileRepository.save(e);
                        },
                        () -> {
                            if (!employees.isEmpty()) {
                                employees.get(0).getTasks().addAll(tasks);
                                employeeProfileRepository.save(employees.get(0));
                            }
                        });

        employeeProfileRepository.delete(employeeProfile);
    }

    @Override
    public EmployeeProfile findById(Long orgId, Long empId) {
        return employeeProfileRepository.findById(empId).orElseThrow();
    }

    @Override
    public void update(Long orgId, Long empId, ChangeEmployeeInfoRequest request) {
        EmployeeProfile employeeProfile = employeeProfileRepository.findById(empId).orElseThrow();

        var role = EmployeeProfile.EmployeeRole.valueOf(request.getRole());
        var speciality = specialityRepository.findById(request.getSpecialityId().longValue()).orElseThrow();

        employeeProfile.setSpeciality(speciality);
        employeeProfile.setRole(role);

        employeeProfileRepository.save(employeeProfile);
    }

    @Override
    public List<Task> getAllTasks(Long orgId, Long empId) {
        return employeeProfileRepository.findById(empId).orElseThrow().getTasks().stream().toList();
    }
}
