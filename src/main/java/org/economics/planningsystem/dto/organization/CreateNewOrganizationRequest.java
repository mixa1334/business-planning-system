package org.economics.planningsystem.dto.organization;

import java.math.BigDecimal;

//to POST /organizations
public class CreateNewOrganizationRequest {
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
}
