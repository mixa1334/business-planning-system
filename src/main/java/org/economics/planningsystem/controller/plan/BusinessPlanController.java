package org.economics.planningsystem.controller.plan;

import org.economics.planningsystem.dto.plan.request.CreateNewBusinessPlanRequest;
import org.economics.planningsystem.dto.plan.response.CreateNewBusinessPlanResponse;
import org.economics.planningsystem.dto.plan.response.GetOrganizationBusinessPlansResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/organizations/{orgId}/business_plans")
public class BusinessPlanController {

    @PostMapping
    public ResponseEntity<CreateNewBusinessPlanResponse> createNewBusinessPlan(@PathVariable Long orgId, @RequestBody CreateNewBusinessPlanRequest createNewBusinessPlanRequest) {
        // TODO: 12/1/2022 create new business plan in org (by org id) and return business plan id
        return null;
    }

    @GetMapping
    public ResponseEntity<GetOrganizationBusinessPlansResponse> getOrganizationBusinessPlans(@PathVariable Long orgId) {
        // TODO: 12/1/2022 get all business plan of organization
        return null;
    }
}
