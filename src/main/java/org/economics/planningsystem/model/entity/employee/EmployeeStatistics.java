package org.economics.planningsystem.model.entity.employee;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;

@Node(labels = "EMPLOYEE_STATISTICS")
public class EmployeeStatistics {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "completed_tasks")
    private Long completedTasks;

    @Property(name = "completed_after_deadline")
    private Long completedAfterDeadline;

    @Property(name = "completed_on_time")
    private Long completedOnTime;

    @Property(name = "efficiency")
    private Long efficiency;

    public Long getId() {
        return id;
    }

    public Long getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(Long completedTasks) {
        this.completedTasks = completedTasks;
    }

    public Long getCompletedAfterDeadline() {
        return completedAfterDeadline;
    }

    public void setCompletedAfterDeadline(Long completedAfterDeadline) {
        this.completedAfterDeadline = completedAfterDeadline;
    }

    public Long getCompletedOnTime() {
        return completedOnTime;
    }

    public void setCompletedOnTime(Long completedOnTime) {
        this.completedOnTime = completedOnTime;
    }

    public Long getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Long efficiency) {
        this.efficiency = efficiency;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeStatistics that = (EmployeeStatistics) o;
        return Objects.equals(id, that.id) && Objects.equals(completedTasks, that.completedTasks) && Objects.equals(completedAfterDeadline, that.completedAfterDeadline) && Objects.equals(completedOnTime, that.completedOnTime) && Objects.equals(efficiency, that.efficiency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, completedTasks, completedAfterDeadline, completedOnTime, efficiency);
    }
}
