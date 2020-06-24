package com.jhipster.demo.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Department.
 */
@Entity
@Table(name = "department")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "dept_name", nullable = false)
    private String deptName;

    @Column(name = "dept_loc")
    private String deptLoc;

    @Column(name = "dept_contact")
    private String deptContact;

    @NotNull
    @Column(name = "university", nullable = false)
    private String university;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public Department deptName(String deptName) {
        this.deptName = deptName;
        return this;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptLoc() {
        return deptLoc;
    }

    public Department deptLoc(String deptLoc) {
        this.deptLoc = deptLoc;
        return this;
    }

    public void setDeptLoc(String deptLoc) {
        this.deptLoc = deptLoc;
    }

    public String getDeptContact() {
        return deptContact;
    }

    public Department deptContact(String deptContact) {
        this.deptContact = deptContact;
        return this;
    }

    public void setDeptContact(String deptContact) {
        this.deptContact = deptContact;
    }

    public String getUniversity() {
        return university;
    }

    public Department university(String university) {
        this.university = university;
        return this;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
        return id != null && id.equals(((Department) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Department{" +
            "id=" + getId() +
            ", deptName='" + getDeptName() + "'" +
            ", deptLoc='" + getDeptLoc() + "'" +
            ", deptContact='" + getDeptContact() + "'" +
            ", university='" + getUniversity() + "'" +
            "}";
    }
}
