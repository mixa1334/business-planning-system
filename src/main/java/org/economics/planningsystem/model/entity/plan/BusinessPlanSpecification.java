package org.economics.planningsystem.model.entity.plan;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.math.BigDecimal;
import java.util.Objects;

@Node(labels = "BUSINESS_PLAN_SPECIFICATION")
public class BusinessPlanSpecification {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "necessary_funds")
    private BigDecimal necessaryFunds;

    @Property(name = "opportunity")
    private String opportunity;

    @Property(name = "profit")
    private BigDecimal profit;

    @Property(name = "risks")
    private String risks;

    public Long getId() {
        return id;
    }

    public BigDecimal getNecessaryFunds() {
        return necessaryFunds;
    }

    public void setNecessaryFunds(BigDecimal necessaryFunds) {
        this.necessaryFunds = necessaryFunds;
    }

    public String getRisks() {
        return risks;
    }

    public void setRisks(String risks) {
        this.risks = risks;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessPlanSpecification that = (BusinessPlanSpecification) o;
        return Objects.equals(id, that.id) && Objects.equals(necessaryFunds, that.necessaryFunds) && Objects.equals(risks, that.risks) && Objects.equals(opportunity, that.opportunity) && Objects.equals(profit, that.profit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, necessaryFunds, risks, opportunity, profit);
    }
}
