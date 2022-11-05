package org.economics.planningsystem.model.entity.organization;

import org.economics.planningsystem.model.entity.employee.User;
import org.economics.planningsystem.model.entity.employee.EmployeeProfile;
import org.economics.planningsystem.model.entity.plan.BusinessPlan;
import org.springframework.data.neo4j.core.schema.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.*;

@Node(labels = "ORGANIZATION")
public class Organization {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String name;

    @Property(name = "available_funds")
    private BigDecimal availableFunds;

    @Relationship(type = "HAS_BUSINESS_PLANS", direction = OUTGOING)
    private Set<BusinessPlan> businessPlans;

    @Relationship(type = "HAS_SPECIALITIES", direction = OUTGOING)
    private Set<Speciality> specialitiesOfOrganization;

    @Relationship(type = "HAS_EMPLOYEES", direction = OUTGOING)
    private Set<EmployeeProfile> employees;

    @Relationship(type = "SENT_REQUEST_TO_JOIN_ORGANIZATION", direction = INCOMING)
    private Set<User> applicationForMembership;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(BigDecimal availableFunds) {
        this.availableFunds = availableFunds;
    }

    public Set<BusinessPlan> getBusinessPlans() {
        return businessPlans;
    }

    public void setBusinessPlans(Set<BusinessPlan> businessPlans) {
        this.businessPlans = businessPlans;
    }

    public Set<Speciality> getSpecialitiesOfOrganization() {
        return specialitiesOfOrganization;
    }

    public void setSpecialitiesOfOrganization(Set<Speciality> specialitiesOfOrganization) {
        this.specialitiesOfOrganization = specialitiesOfOrganization;
    }

    public Set<EmployeeProfile> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeProfile> employees) {
        this.employees = employees;
    }

    public Set<User> getApplicationForMembership() {
        return applicationForMembership;
    }

    public void setApplicationForMembership(Set<User> applicationForMembership) {
        this.applicationForMembership = applicationForMembership;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(availableFunds, that.availableFunds) && Objects.equals(businessPlans, that.businessPlans) && Objects.equals(specialitiesOfOrganization, that.specialitiesOfOrganization) && Objects.equals(employees, that.employees) && Objects.equals(applicationForMembership, that.applicationForMembership);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, availableFunds, businessPlans, specialitiesOfOrganization, employees, applicationForMembership);
    }
}
