package org.economics.planningsystem.model.entity.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.economics.planningsystem.model.entity.organization.Speciality;
import org.economics.planningsystem.model.entity.plan.Task;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.*;

@Node(labels = "EMPLOYEE_PROFILE")
public class EmployeeProfile {
    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "HAS_TASKS", direction = OUTGOING)
    @JsonIgnore
    private Set<Task> tasks;

    @Relationship(type = "HAS_SPECIALITY", direction = OUTGOING)
    private Speciality speciality;

    @Property(name = "role")
    private EmployeeRole role;

    public enum EmployeeRole {
        EMPLOYEE("EMPLOYEE", "EMPLOYEE"),
        ANALYST("ANALYST", "EMPLOYEE", "ANALYST"),
        DIRECTOR("DIRECTOR", "EMPLOYEE", "ANALYST", "DIRECTOR");

        private final String permission;
        private final Set<String> authorities;

        EmployeeRole(String permission, String... authorities) {
            this.permission = permission;
            this.authorities = Set.of(authorities);
        }

        public final Set<String> getAuthorities() {
            return authorities;
        }

        public final String getPermission() {
            return permission;
        }
    }

    public Long getId() {
        return id;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeProfile that = (EmployeeProfile) o;
        return Objects.equals(id, that.id) && Objects.equals(tasks, that.tasks) && Objects.equals(speciality, that.speciality) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tasks, speciality, role);
    }
}
