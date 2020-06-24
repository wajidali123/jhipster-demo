package com.jhipster.demo.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.jhipster.demo.domain.Department} entity.
 */
public class DepartmentDTO implements Serializable {

    private Long id;

    @NotNull
    private String deptName;

    private String deptLoc;

    private String deptContact;

    @NotNull
    private String university;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptLoc() {
        return deptLoc;
    }

    public void setDeptLoc(String deptLoc) {
        this.deptLoc = deptLoc;
    }

    public String getDeptContact() {
        return deptContact;
    }

    public void setDeptContact(String deptContact) {
        this.deptContact = deptContact;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DepartmentDTO departmentDTO = (DepartmentDTO) o;
        if (departmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), departmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
            "id=" + getId() +
            ", deptName='" + getDeptName() + "'" +
            ", deptLoc='" + getDeptLoc() + "'" +
            ", deptContact='" + getDeptContact() + "'" +
            ", university='" + getUniversity() + "'" +
            "}";
    }
}
