package com.jhipster.demo.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.jhipster.demo.domain.University} entity.
 */
public class UniversityDTO implements Serializable {

    private Long id;

    @NotNull
    private String uniName;

    private String uniLoc;

    private String uniContact;

    @NotNull
    private String city;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getUniLoc() {
        return uniLoc;
    }

    public void setUniLoc(String uniLoc) {
        this.uniLoc = uniLoc;
    }

    public String getUniContact() {
        return uniContact;
    }

    public void setUniContact(String uniContact) {
        this.uniContact = uniContact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UniversityDTO universityDTO = (UniversityDTO) o;
        if (universityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), universityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UniversityDTO{" +
            "id=" + getId() +
            ", uniName='" + getUniName() + "'" +
            ", uniLoc='" + getUniLoc() + "'" +
            ", uniContact='" + getUniContact() + "'" +
            ", city='" + getCity() + "'" +
            "}";
    }
}
