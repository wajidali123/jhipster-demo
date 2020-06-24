package com.jhipster.demo.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "fname", nullable = false)
    private String fname;

    @Column(name = "mname")
    private String mname;

    @NotNull
    @Column(name = "lname", nullable = false)
    private String lname;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "dob")
    private LocalDate dob;

    @NotNull
    @Column(name = "dept", nullable = false)
    private String dept;

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

    public String getFname() {
        return fname;
    }

    public Student fname(String fname) {
        this.fname = fname;
        return this;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public Student mname(String mname) {
        this.mname = mname;
        return this;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public Student lname(String lname) {
        this.lname = lname;
        return this;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public Student email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Student dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getDept() {
        return dept;
    }

    public Student dept(String dept) {
        this.dept = dept;
        return this;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getUniversity() {
        return university;
    }

    public Student university(String university) {
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
        if (!(o instanceof Student)) {
            return false;
        }
        return id != null && id.equals(((Student) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", fname='" + getFname() + "'" +
            ", mname='" + getMname() + "'" +
            ", lname='" + getLname() + "'" +
            ", email='" + getEmail() + "'" +
            ", dob='" + getDob() + "'" +
            ", dept='" + getDept() + "'" +
            ", university='" + getUniversity() + "'" +
            "}";
    }
}
