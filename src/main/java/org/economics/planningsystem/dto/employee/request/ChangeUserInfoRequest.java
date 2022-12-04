package org.economics.planningsystem.dto.employee.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

// PUT to /users/{id}
public class ChangeUserInfoRequest {
    @Pattern(regexp = "^[a-zA-Z]{3,45}$")
    private String name;

    @Pattern(regexp = "^[a-zA-Z]{3,45}$")
    private String surname;

    @Min(18)
    @Max(60)
    private Integer age;

    @Pattern(regexp = "^\\+375 \\((29|44|33|25)\\) [0-9]{3}-[0-9]{2}-[0-9]{2}$")
    private String phoneNumber;

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
}
