package org.economics.planningsystem.security.expression;

import org.economics.planningsystem.model.service.auth.AuthService;
import org.economics.planningsystem.security.user.BpsUserDetails;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public class OrganizationSecurityExpression extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
    private final AuthService authService;
    private HttpServletRequest request;
    private Object filterObject;
    private Object returnObject;
    private Object target;

    public OrganizationSecurityExpression(Authentication authentication, AuthService authenticationService) {
        super(authentication);
        this.authService = authenticationService;
    }

    public boolean hasOrganization() {
        Long organizationId = getUserDetails().getOrganizationId();
        return organizationId != null;
    }

    public boolean isAMemberOfOrganization(Long organizationId) {
        Long employeeOrganizationId = getUserDetails().getOrganizationId();
        return organizationId != null && organizationId.equals(employeeOrganizationId);
    }

    public boolean hasEmployeeProfile(Long empId) {
        Long profileId = getUserDetails().getProfileId();
        return profileId != null && profileId.equals(empId);
    }

    public boolean isTaskOwner(Long taskId) {
        Long profileId = getUserDetails().getProfileId();
        return profileId != null && taskId != null && authService.isOwnerOfTask(taskId, profileId);
    }

    public boolean hasUserId(Long userId) {
        Long originalUserId = getUserDetails().getUserId();
        return originalUserId.equals(userId);
    }

    private BpsUserDetails getUserDetails() {
        return (BpsUserDetails) authentication.getPrincipal();
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