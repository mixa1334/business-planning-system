package org.economics.planningsystem.model.service.organization;

import java.util.Optional;

public interface OrganizationService {
    Optional<Long> findOrganizationIdByProfileId(Long profileId);
}
