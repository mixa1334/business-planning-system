package org.economics.planningsystem.dto.plan.request;

import org.economics.planningsystem.model.entity.plan.BusinessPlan;
import org.economics.planningsystem.model.entity.plan.BusinessPlanSpecification;
import org.economics.planningsystem.model.entity.plan.BusinessPlanStatistics;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// POST to /organizations/{orgId}/business_plans
public class CreateNewBusinessPlanRequest {
    private String title;

    private BigDecimal necessaryFunds;

    private String opportunity;

    private BigDecimal profit;

    private String deadline;

    private String risks;

    public BusinessPlan toBusinessPlan() {
        BusinessPlanSpecification businessPlanSpecification = new BusinessPlanSpecification();
        businessPlanSpecification.setRisks(risks);
        businessPlanSpecification.setProfit(profit);
        businessPlanSpecification.setOpportunity(opportunity);
        businessPlanSpecification.setNecessaryFunds(necessaryFunds);

        BusinessPlanStatistics businessPlanStatistics = new BusinessPlanStatistics();
        businessPlanStatistics.setCompletedTasks(0L);
        businessPlanStatistics.setAllTasks(0L);
        businessPlanStatistics.setDeadline(LocalDate.parse(deadline));

        BusinessPlan businessPlan = new BusinessPlan();
        businessPlan.setTitle(title);
        businessPlan.setStatus(BusinessPlan.BusinessPlanStatus.APPROVED);
        businessPlan.setBusinessPlanSpecification(businessPlanSpecification);
        businessPlan.setBusinessPlanStatistics(businessPlanStatistics);

        return businessPlan;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getNecessaryFunds() {
        return necessaryFunds;
    }

    public void setNecessaryFunds(BigDecimal necessaryFunds) {
        this.necessaryFunds = necessaryFunds;
    }

    public String getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(String opportunity) {
        this.opportunity = opportunity;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getRisks() {
        return risks;
    }

    public void setRisks(String risks) {
        this.risks = risks;
    }
}
