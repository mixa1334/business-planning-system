package org.economics.planningsystem.security.expression;

import org.economics.planningsystem.model.service.organization.OrganizationService;
import org.economics.planningsystem.security.user.BpsUserDetails;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public class OrganizationSecurityExpression extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
    private final OrganizationService service;
    private HttpServletRequest request;
    private Object filterObject;
    private Object returnObject;
    private Object target;

    public OrganizationSecurityExpression(Authentication authentication, OrganizationService service) {
        super(authentication);
        this.service = service;
    }

    // TODO: 11/30/2022 refactor
    public boolean isInOrganization(Long organizationId) {
        Long profileId = ((BpsUserDetails) authentication.getPrincipal()).getProfileId();
        if (profileId == null) {
            return false;
        }
        Long employeeOrganizationId = service.findOrganizationIdByProfileId(profileId);
        return employeeOrganizationId.equals(organizationId);
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