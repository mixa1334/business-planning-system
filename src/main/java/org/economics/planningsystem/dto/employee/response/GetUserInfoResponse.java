package org.economics.planningsystem.dto.employee.response;

import org.economics.planningsystem.model.entity.employee.EmployeeStatistics;
import org.economics.planningsystem.model.entity.employee.User;

// from GET /users/{id}
public class GetUserInfoResponse {
    private String name;

    private String surname;

    private Integer age;

    private String phoneNumber;

    private Long completedTasks;

    private Long competedAfterDeadLine;

    private Long completedOnTime;

    private Long efficiency;

    public static GetUserInfoResponse build(User user) {
        GetUserInfoResponse response = new GetUserInfoResponse();
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setAge(user.getAge());
        response.setPhoneNumber(user.getPhoneNumber());
        EmployeeStatistics statistics = user.getStatistics();
        response.setCompletedTasks(statistics.getCompletedTasks());
        response.setCompetedAfterDeadLine(statistics.getCompletedAfterDeadline());
        response.setCompletedOnTime(statistics.getCompletedOnTime());
        response.setEfficiency(statistics.getEfficiency());
        return response;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(Long completedTasks) {
        this.completedTasks = completedTasks;
    }

    public Long getCompetedAfterDeadLine() {
        return competedAfterDeadLine;
    }

    public void setCompetedAfterDeadLine(Long competedAfterDeadLine) {
        this.competedAfterDeadLine = competedAfterDeadLine;
    }

    public Long getCompletedOnTime() {
        return completedOnTime;
    }

    public void setCompletedOnTime(Long completedOnTime) {
        this.completedOnTime = completedOnTime;
    }

    public Long getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Long efficiency) {
        this.efficiency = efficiency;
    }
}
