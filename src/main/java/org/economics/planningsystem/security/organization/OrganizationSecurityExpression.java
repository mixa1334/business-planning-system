package org.economics.planningsystem.security.organization;

import org.economics.planningsystem.security.SecurityUserDetails;
import org.economics.planningsystem.security.organization.service.OrganizationSecurityService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class OrganizationSecurityExpression extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
    private final OrganizationSecurityService service;
    private HttpServletRequest request;
    private Object filterObject;
    private Object returnObject;
    private Object target;

    public OrganizationSecurityExpression(Authentication authentication, OrganizationSecurityService service) {
        super(authentication);
        this.service = service;
    }

    public boolean isInOrganization(Long organizationId) {
        Long profileId = ((SecurityUserDetails) authentication.getPrincipal()).getProfileId();
        Optional<Long> employeeOrganizationId = service.findOrganizationIdByProfileId(profileId);
        return employeeOrganizationId.isPresent() && employeeOrganizationId.get().equals(organizationId);
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return target;
    }
}