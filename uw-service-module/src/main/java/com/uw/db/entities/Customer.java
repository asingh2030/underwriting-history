package com.uw.db.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Customer")
public class Customer implements Serializable {
  /*  @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;*/
    @Id
    private String ssn;
    private String name;
    private String gender;
    private Date dob;
    private String address;
    private String designation;
    private String maritalStatus;
    private String healthStatus;
    private String healthReason;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getHealthReason() {
        return healthReason;
    }

    public void setHealthReason(String healthReason) {
        this.healthReason = healthReason;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Customer(){
    }

    public Customer(String ssn, String name, String gender, Date dob) {
        this.ssn = ssn;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getSsn(), customer.getSsn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSsn());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
