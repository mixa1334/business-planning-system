package org.economics.planningsystem.dto.organization.request;

// POST to /organizations/{orgId}/applications
public class RequestToJoinOrganization {
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
