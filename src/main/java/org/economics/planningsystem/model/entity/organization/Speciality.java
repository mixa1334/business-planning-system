package org.economics.planningsystem.model.entity.organization;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;

@Node(labels = "SPECIALITY")
public class Speciality {
    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "name")
    private String name;
    @Property(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speciality position = (Speciality) o;
        return Objects.equals(id, position.id) && Objects.equals(name, position.name) && Objects.equals(description, position.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
