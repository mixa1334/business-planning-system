package org.economics.planningsystem.dto.employee.response;

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
