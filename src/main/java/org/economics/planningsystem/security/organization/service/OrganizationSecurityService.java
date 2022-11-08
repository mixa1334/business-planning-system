package org.economics.planningsystem.security.organization.service;

import java.util.Optional;

public interface OrganizationSecurityService {
    Optional<Long> findOrganizationIdByProfileId(Long profileId);
}
