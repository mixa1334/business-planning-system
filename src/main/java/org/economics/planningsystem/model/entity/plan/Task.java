package org.economics.planningsystem.model.entity.plan;

import org.economics.planningsystem.model.entity.organization.Speciality;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDate;
import java.util.Objects;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.*;

@Node(labels = "TASK")
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "deadline")
    private LocalDate deadline;

    @Property(name = "title")
    private String title;

    @Property(name = "description")
    private String description;

    @Relationship(type = "NECESSARY_SPECIALITY", direction = OUTGOING)
    private Speciality necessarySpeciality;

    @Property(name = "status")
    private TaskStatus taskStatus;

    public enum TaskStatus{
        IN_PROCESS,
        DONE
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Speciality getNecessarySpeciality() {
        return necessarySpeciality;
    }

    public void setNecessarySpeciality(Speciality necessarySpeciality) {
        this.necessarySpeciality = necessarySpeciality;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(deadline, task.deadline) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(necessarySpeciality, task.necessarySpeciality) && taskStatus == task.taskStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deadline, title, description, necessarySpeciality, taskStatus);
    }
}
