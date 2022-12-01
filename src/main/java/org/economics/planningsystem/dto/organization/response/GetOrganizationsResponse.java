package org.economics.planningsystem.dto.organization.response;

import org.economics.planningsystem.model.entity.organization.Organization;

import java.util.List;

//from GET /organizations
public class GetOrganizationsResponse {
    private List<Organization> organizations;

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }
}
