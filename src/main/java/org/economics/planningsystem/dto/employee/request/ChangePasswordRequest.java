package org.economics.planningsystem.dto.employee.request;

import javax.validation.constraints.NotBlank;

//PUT to /users/{id}/password
public class ChangePasswordRequest {
    @NotBlank
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
