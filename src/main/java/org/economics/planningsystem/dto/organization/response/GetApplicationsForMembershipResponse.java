package org.economics.planningsystem.dto.organization.response;
import org.economics.planningsystem.model.entity.employee.User;

import java.util.List;

// from GET /organizations/{id}/applications
public class GetApplicationsForMembershipResponse {
    private List<User> applicationForMembership;

    public List<User> getApplicationForMembership() {
        return applicationForMembership;
    }

    public void setApplicationForMembership(List<User> applicationForMembership) {
        this.applicationForMembership = applicationForMembership;
    }
}
