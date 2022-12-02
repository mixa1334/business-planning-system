package org.economics.planningsystem.model.entity.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.*;

@Node(labels = "USER")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String name;

    @Property(name = "surname")
    private String surname;

    @Property(name = "age")
    private Integer age;

    @Property(name = "phone_number")
    private String phoneNumber;

    @Property(name = "login")
    @JsonIgnore
    private String login;

    @Property(name = "password")
    @JsonIgnore
    private String password;

    @Relationship(type = "HAS_PROFILE", direction = OUTGOING)
    private EmployeeProfile profile;

    @Relationship(type = "HAS_PROFILE_STATISTICS", direction = OUTGOING)
    private EmployeeStatistics statistics;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeProfile getProfile() {
        return profile;
    }

    public void setProfile(EmployeeProfile profile) {
        this.profile = profile;
    }

    public EmployeeStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(EmployeeStatistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(age, user.age) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(profile, user.profile) && Objects.equals(statistics, user.statistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, phoneNumber, login, password, profile, statistics);
    }
}
