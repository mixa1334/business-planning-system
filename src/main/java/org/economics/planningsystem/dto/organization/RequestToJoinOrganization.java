package org.economics.planningsystem.dto.organization;

import com.fasterxml.jackson.annotation.JsonProperty;

// POST to /organizations/{orgId}/applications
public class RequestToJoinOrganization {
    @JsonProperty("user_id")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
