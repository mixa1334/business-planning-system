package org.economics.planningsystem.dto.organization.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

// PUT to /organizations/{orgId}
public class ChangeOrganizationInfoRequest {
    @JsonProperty("new_name")
    private String newName;

    @JsonProperty("new_funds")
    private BigDecimal newFunds;

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public BigDecimal getNewFunds() {
        return newFunds;
    }

    public void setNewFunds(BigDecimal newFunds) {
        this.newFunds = newFunds;
    }
}
