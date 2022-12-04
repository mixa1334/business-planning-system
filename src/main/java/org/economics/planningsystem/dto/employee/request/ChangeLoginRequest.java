package org.economics.planningsystem.dto.employee.request;

import javax.validation.constraints.NotBlank;

//PUT to /users/{id}/login
public class ChangeLoginRequest {
    @NotBlank
    private String newLogin;

    public String getNewLogin() {
        return newLogin;
    }

    public void setNewLogin(String newLogin) {
        this.newLogin = newLogin;
    }
}
