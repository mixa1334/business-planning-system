package org.economics.planningsystem.controller.plan;

import org.economics.planningsystem.dto.plan.request.CreateNewBusinessPlanRequest;
import org.economics.planningsystem.dto.plan.response.CreateNewBusinessPlanResponse;
import org.economics.planningsystem.dto.plan.response.GetOrganizationBusinessPlansResponse;
import org.economics.planningsystem.model.entity.plan.BusinessPlan;
import org.economics.planningsystem.model.service.plan.BusinessPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/organizations/{orgId}/business_plans")
public class BusinessPlanController {

    private final BusinessPlanService businessPlanService;

    @Autowired
    public BusinessPlanController(BusinessPlanService businessPlanService) {
        this.businessPlanService = businessPlanService;
    }

    @PostMapping
    public ResponseEntity<CreateNewBusinessPlanResponse> createNewBusinessPlan(
            @PathVariable Long orgId,
            @RequestBody CreateNewBusinessPlanRequest request
    ) {
        CreateNewBusinessPlanResponse response = new CreateNewBusinessPlanResponse();
        Long planId = businessPlanService.create(orgId, request.toBusinessPlan());
        response.setPlanId(planId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GetOrganizationBusinessPlansResponse> getOrganizationBusinessPlans(@PathVariable Long orgId) {
        GetOrganizationBusinessPlansResponse response = new GetOrganizationBusinessPlansResponse();
        List<BusinessPlan> plans = businessPlanService.findAll(orgId);
        response.setBusinessPlans(plans);
        return ResponseEntity.ok(response);
    }
}
