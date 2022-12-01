package org.economics.planningsystem.dto.employee.request;

import com.fasterxml.jackson.annotation.JsonProperty;

//PUT to /users/{id}/password
public class ChangePasswordRequest {
    @JsonProperty("new_password")
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
