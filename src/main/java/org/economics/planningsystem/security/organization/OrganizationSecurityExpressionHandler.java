package org.economics.planningsystem.security.organization;

import org.aopalliance.intercept.MethodInvocation;
import org.economics.planningsystem.security.organization.service.impl.OrganizationSecurityServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class OrganizationSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
    private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
    private ApplicationContext applicationContext;

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        OrganizationSecurityServiceImpl service = applicationContext.getBean(OrganizationSecurityServiceImpl.class);
        OrganizationSecurityExpression guard = new OrganizationSecurityExpression(authentication, service);
        guard.setTrustResolver(trustResolver);
        guard.setPermissionEvaluator(getPermissionEvaluator());
        guard.setRoleHierarchy(getRoleHierarchy());
        return guard;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
        this.applicationContext = applicationContext;
    }
}
