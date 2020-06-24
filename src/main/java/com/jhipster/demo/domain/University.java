package com.jhipster.demo.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A University.
 */
@Entity
@Table(name = "university")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class University implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "uni_name", nullable = false)
    private String uniName;

    @Column(name = "uni_loc")
    private String uniLoc;

    @Column(name = "uni_contact")
    private String uniContact;

    @NotNull
    @Column(name = "city", nullable = false)
    private String city;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniName() {
        return uniName;
    }

    public University uniName(String uniName) {
        this.uniName = uniName;
        return this;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getUniLoc() {
        return uniLoc;
    }

    public University uniLoc(String uniLoc) {
        this.uniLoc = uniLoc;
        return this;
    }

    public void setUniLoc(String uniLoc) {
        this.uniLoc = uniLoc;
    }

    public String getUniContact() {
        return uniContact;
    }

    public University uniContact(String uniContact) {
        this.uniContact = uniContact;
        return this;
    }

    public void setUniContact(String uniContact) {
        this.uniContact = uniContact;
    }

    public String getCity() {
        return city;
    }

    public University city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof University)) {
            return false;
        }
        return id != null && id.equals(((University) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "University{" +
            "id=" + getId() +
            ", uniName='" + getUniName() + "'" +
            ", uniLoc='" + getUniLoc() + "'" +
            ", uniContact='" + getUniContact() + "'" +
            ", city='" + getCity() + "'" +
            "}";
    }
}
