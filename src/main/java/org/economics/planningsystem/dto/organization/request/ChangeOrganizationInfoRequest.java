package org.economics.planningsystem.dto.organization.request;

import org.economics.planningsystem.model.entity.organization.Organization;

import java.math.BigDecimal;

// PUT to /organizations/{orgId}
public class ChangeOrganizationInfoRequest {
    private String newName;

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

    public Organization getOrganization(){
        Organization organization = new Organization();
        organization.setName(newName);
        organization.setAvailableFunds(newFunds);
        return organization;
    }
}
