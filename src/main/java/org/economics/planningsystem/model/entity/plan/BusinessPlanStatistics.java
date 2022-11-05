package org.economics.planningsystem.model.entity.plan;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.time.LocalDate;
import java.util.Objects;

@Node(labels = "BUSINESS_PLAN_STATISTICS")
public class BusinessPlanStatistics {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "deadline")
    private LocalDate deadline;

    @Property(name = "all_tasks")
    private Long allTasks;

    @Property(name = "completed_tasks")
    private Long completedTasks;

    public Long getId() {
        return id;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Long getAllTasks() {
        return allTasks;
    }

    public void setAllTasks(Long allTasks) {
        this.allTasks = allTasks;
    }

    public Long getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(Long completedTasks) {
        this.completedTasks = completedTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessPlanStatistics that = (BusinessPlanStatistics) o;
        return Objects.equals(id, that.id) && Objects.equals(deadline, that.deadline) && Objects.equals(allTasks, that.allTasks) && Objects.equals(completedTasks, that.completedTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deadline, allTasks, completedTasks);
    }
}
