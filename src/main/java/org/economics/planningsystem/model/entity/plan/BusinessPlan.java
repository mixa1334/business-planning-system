package org.economics.planningsystem.model.entity.plan;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.*;

@Node(labels = "BUSINESS_PLAN")
public class BusinessPlan {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "title")
    private String title;

    @Relationship(type = "HAS_PLAN_SPECIFICATION", direction = OUTGOING)
    private BusinessPlanSpecification businessPlanSpecification;

    @Relationship(type = "HAS_PLAN_STATISTICS", direction = OUTGOING)
    private BusinessPlanStatistics businessPlanStatistics;

    @Relationship(type = "HAS_TASKS", direction = OUTGOING)
    private Set<Task> tasks;

    @Property(name = "status")
    private BusinessPlanStatus status;

    public enum BusinessPlanStatus {
        APPROVED,
        NOT_APPROVED,
        COMPLETED
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BusinessPlanSpecification getBusinessPlanSpecification() {
        return businessPlanSpecification;
    }

    public void setBusinessPlanSpecification(BusinessPlanSpecification businessPlanSpecification) {
        this.businessPlanSpecification = businessPlanSpecification;
    }

    public BusinessPlanStatistics getBusinessPlanStatistics() {
        return businessPlanStatistics;
    }

    public void setBusinessPlanStatistics(BusinessPlanStatistics businessPlanStatistics) {
        this.businessPlanStatistics = businessPlanStatistics;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public BusinessPlanStatus getStatus() {
        return status;
    }

    public void setStatus(BusinessPlanStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessPlan that = (BusinessPlan) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(businessPlanSpecification, that.businessPlanSpecification) && Objects.equals(businessPlanStatistics, that.businessPlanStatistics) && Objects.equals(tasks, that.tasks) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, businessPlanSpecification, businessPlanStatistics, tasks, status);
    }
}
