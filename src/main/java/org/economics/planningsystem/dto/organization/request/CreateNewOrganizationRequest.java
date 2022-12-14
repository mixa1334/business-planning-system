package org.economics.planningsystem.dto.organization.request;

import java.math.BigDecimal;

//to POST /organizations
public class CreateNewOrganizationRequest {
    private Long userId;

    private String name;

    private BigDecimal funds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
