package org.economics.planningsystem.dto.auth.request;

public class GetUserDetailsRequest {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
