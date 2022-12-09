package org.economics.planningsystem.model.service.employee;

import org.economics.planningsystem.dto.employee.request.ChangeEmployeeInfoRequest;
import org.economics.planningsystem.model.entity.employee.EmployeeProfile;
import org.economics.planningsystem.model.entity.plan.Task;

import java.util.List;

public interface EmployeeService {

    List<EmployeeProfile> findAll(Long orgId);

    void delete(Long orgId, Long empId);

    EmployeeProfile findById(Long orgId, Long empId);

    void update(Long orgId, Long empId, ChangeEmployeeInfoRequest request);

    List<Task> getAllTasks(Long orgId, Long empId);

}
