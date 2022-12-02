package org.economics.planningsystem.dto.organization.response;

import java.math.BigDecimal;

// from GET /organizations/{id}
public class GetOrganizationInfoResponse {
    private String organizationName;

    private BigDecimal availableFunds;

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public BigDecimal getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(BigDecimal availableFunds) {
        this.availableFunds = availableFunds;
    }
}
