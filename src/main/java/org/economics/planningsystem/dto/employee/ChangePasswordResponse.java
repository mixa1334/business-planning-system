package org.economics.planningsystem.dto.employee;

// from PUT /users/{id}/password
public class ChangePasswordResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
