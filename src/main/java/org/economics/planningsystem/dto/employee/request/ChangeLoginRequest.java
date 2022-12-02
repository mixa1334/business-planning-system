package org.economics.planningsystem.dto.employee.request;

//PUT to /users/{id}/login
public class ChangeLoginRequest {
    private String newLogin;

    public String getNewLogin() {
        return newLogin;
    }

    public void setNewLogin(String newLogin) {
        this.newLogin = newLogin;
    }
}
