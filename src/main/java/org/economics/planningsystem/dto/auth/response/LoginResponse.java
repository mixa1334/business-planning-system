package org.economics.planningsystem.dto.auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// from POST /auth/login and POST /auth/signup
public class LoginResponse {
    private String token;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("statistics_id")
    private Long statisticsId;

    private List<String> roles;

    @JsonProperty("organization_id")
    private Long organizationId;

    @JsonProperty("profile_id")
    private Long profileId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStatisticsId() {
        return statisticsId;
    }

    public void setStatisticsId(Long statisticsId) {
        this.statisticsId = statisticsId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
}
