package org.economics.planningsystem.dto.employee.request;

//PUT to /users/{id}/password
public class ChangePasswordRequest {
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
