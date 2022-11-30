package org.economics.planningsystem.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

//PUT to /users/{id}/login
public class ChangeLoginRequest {
    @JsonProperty("new_login")
    private String newLogin;

    public String getNewLogin() {
        return newLogin;
    }

    public void setNewLogin(String newLogin) {
        this.newLogin = newLogin;
    }
}
