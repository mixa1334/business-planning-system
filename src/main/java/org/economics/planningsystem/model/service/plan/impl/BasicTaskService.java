package org.economics.planningsystem.model.service.plan.impl;

import org.economics.planningsystem.dto.plan.request.CreateNewTaskRequest;
import org.economics.planningsystem.model.entity.employee.EmployeeProfile;
import org.economics.planningsystem.model.entity.organization.Organization;
import org.economics.planningsystem.model.entity.organization.Speciality;
import org.economics.planningsystem.model.entity.plan.BusinessPlan;
import org.economics.planningsystem.model.entity.plan.BusinessPlanStatistics;
import org.economics.planningsystem.model.entity.plan.Task;
import org.economics.planningsystem.model.repository.employee.EmployeeProfileRepository;
import org.economics.planningsystem.model.repository.organization.OrganizationRepository;
import org.economics.planningsystem.model.repository.organization.SpecialityRepository;
import org.economics.planningsystem.model.repository.plan.BusinessPlanRepository;
import org.economics.planningsystem.model.repository.plan.TaskRepository;
import org.economics.planningsystem.model.service.plan.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BasicTaskService implements TaskService {

    private final TaskRepository taskRepository;
    private final BusinessPlanRepository businessPlanRepository;
    private final SpecialityRepository specialityRepository;
    private final EmployeeProfileRepository employeeProfileRepository;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public BasicTaskService(TaskRepository taskRepository,
                            BusinessPlanRepository businessPlanRepository,
                            SpecialityRepository specialityRepository,
                            EmployeeProfileRepository employeeProfileRepository,
                            OrganizationRepository organizationRepository) {
        this.taskRepository = taskRepository;
        this.businessPlanRepository = businessPlanRepository;
        this.specialityRepository = specialityRepository;
        this.employeeProfileRepository = employeeProfileRepository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public void complete(Long orgId, Long planId, Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setTaskStatus(Task.TaskStatus.DONE);

        BusinessPlan plan = businessPlanRepository.findById(planId).orElseThrow();
        BusinessPlanStatistics statistics = plan.getBusinessPlanStatistics();
        statistics.setCompletedTasks(statistics.getCompletedTasks() + 1);

        if (statistics.getAllTasks().equals(statistics.getCompletedTasks())) {
            plan.setStatus(BusinessPlan.BusinessPlanStatus.COMPLETED);
        }

        businessPlanRepository.save(plan);
        taskRepository.save(task);
        taskRepository.deleteTaskFromEmployee(taskId);
    }

    @Override
    public void create(Long orgId, Long planId, CreateNewTaskRequest request) {
        Task task = toTask(request);
        task.setBusinessPlanId(planId);
        BusinessPlan businessPlan = businessPlanRepository.findById(planId).orElseThrow();

        if (businessPlan.getTasks().add(task)) {
            BusinessPlanStatistics statistics = businessPlan.getBusinessPlanStatistics();
            statistics.setAllTasks(statistics.getAllTasks() + 1);

            Organization organization = organizationRepository.findById(orgId).orElseThrow();

            EmployeeProfile employeeProfile = organization.getEmployees().stream()
                    .filter(e -> e.getSpeciality().equals(task.getNecessarySpeciality()))
                    .sorted(Comparator.comparing(e -> e.getTasks().size()))
                    .findAny()
                    .orElseThrow();

            employeeProfile.getTasks().add(task);

            businessPlanRepository.save(businessPlan);
            employeeProfileRepository.save(employeeProfile);
        }
    }

    private Task toTask(CreateNewTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDeadline(LocalDate.parse(request.getDeadline()));
        task.setTaskStatus(Task.TaskStatus.IN_PROCESS);


        Speciality speciality = specialityRepository.findById(request.getNecessarySpecialityId()).orElseThrow();
        task.setNecessarySpeciality(speciality);

        return task;
    }

}
